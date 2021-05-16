package br.com.zupacademy.jonathan.proposta.carteira;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.jonathan.proposta.cartao.Cartao;
import br.com.zupacademy.jonathan.proposta.cartao.CartaoRepository;
import br.com.zupacademy.jonathan.proposta.gateway.cartao.CartaoClient;
import br.com.zupacademy.jonathan.proposta.utils.ExecutorTransacao;
import feign.FeignException;

@RestController
@RequestMapping("/carteiras")
public class CarteiraController {

	@Autowired
	private CartaoClient cartaoClient;
	@Autowired
	private ExecutorTransacao executorTransacao;
	@Autowired
    private CartaoRepository cartaoRepository;
	@Autowired
	private CarteiraRepository carteiraRepository;
	
	private final Logger logger = LoggerFactory.getLogger(CarteiraController.class);
	
	@PostMapping("/{idCartao}")
	public ResponseEntity<?> associarCarteira(@PathVariable("idCartao") Long id,
												@RequestBody @Valid CarteiraRequest request,
												UriComponentsBuilder uriBuilder){
		Optional<Cartao> cartao = cartaoRepository.findById(id);
        if(cartao.isEmpty()){
        	logger.warn("Cartão {} não encontrado=", id);
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado");
        }
        
        if(carteiraRepository.existsByCartaoAndCarteira(cartao.get(), request.getCarteira())) {
        	logger.warn("Cartão já associado");
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cartão já está associada com a cateira");
        }
		associarCartao(cartao.get(), request);
        return ResponseEntity.ok().build();
	}
	
	public void associarCartao(Cartao cartao, CarteiraRequest request) {
		try {
			CarteiraResponse carteiraResponse = cartaoClient.associarCarteira(cartao.getNumero(), request);
			Carteira novaCarteira = carteiraResponse.toModel(request, cartao);
			executorTransacao.salvaEComita(novaCarteira);
		} catch (FeignException.UnprocessableEntity e) {
			logger.error(" Erro = {}", e.toString());
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Falha ao tentar associar o cartão");
		}
	}
	
}
