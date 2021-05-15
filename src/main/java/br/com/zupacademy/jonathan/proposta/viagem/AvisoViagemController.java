package br.com.zupacademy.jonathan.proposta.viagem;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
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
import br.com.zupacademy.jonathan.proposta.cartao.Cartao;
import br.com.zupacademy.jonathan.proposta.cartao.CartaoRepository;
import br.com.zupacademy.jonathan.proposta.gateway.cartao.CartaoClient;
import br.com.zupacademy.jonathan.proposta.utils.ExecutorTransacao;

@RestController
@RequestMapping("/viagens")
public class AvisoViagemController {
	
	@Autowired
    private CartaoRepository cartaoRepository;
	@Autowired
	private ExecutorTransacao executorTransacao;
	@Autowired
	private CartaoClient cartaoClient;
	
	private final Logger logger = LoggerFactory.getLogger(AvisoViagemController.class);

	@PostMapping("/{idCartao}")
	public ResponseEntity<?> criarAvisoViagem(@PathVariable("idCartao") Long id,
												HttpServletRequest servletRequest,
												@RequestBody @Valid AvisoViagemRequest request){
		
		Optional<Cartao> cartao = cartaoRepository.findById(id);
        if(cartao.isEmpty()){
        	logger.warn("Cartão {} não encontrado=", id);
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado");
        }
        
        try {
			AvisoViagem novoAvisoViagem = request.toModel(cartao.get(), servletRequest.getLocalAddr(), 
															servletRequest.getHeader("User-Agent"));
			cartaoClient.avisarViagem(cartao.get().getNumero(), request);
			cartao.get().setViagem(novoAvisoViagem);
			executorTransacao.atualizaEComita(cartao.get());
			logger.info("Viagem registrada com sucesso!");	
		} catch (Exception e) {
			logger.error("Ocorreu um erro {}", e.toString());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ocorreu um erro");		
		}
        return ResponseEntity.ok().build(); 
	}
	
}
