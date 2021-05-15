package br.com.zupacademy.jonathan.proposta.bloqueio;

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
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.jonathan.proposta.cartao.Cartao;
import br.com.zupacademy.jonathan.proposta.cartao.CartaoRepository;
import br.com.zupacademy.jonathan.proposta.gateway.cartao.CartaoClient;
import br.com.zupacademy.jonathan.proposta.utils.ExecutorTransacao;
import feign.FeignException;

@RestController
@RequestMapping("/bloqueios")
public class BloqueioController {

	@Autowired
    private CartaoRepository cartaoRepository;
	@Autowired
	private ExecutorTransacao executorTransacao;
	@Autowired
	private CartaoClient cartaoClient;
	
	private final Logger logger = LoggerFactory.getLogger(BloqueioController.class);
	
	@PostMapping("/{idCartao}")
	public ResponseEntity<?> bloquearCartao(@PathVariable("idCartao") Long id,
												HttpServletRequest servletRequest,
												@RequestBody @Valid BloqueioRequest request){
		
		Optional<Cartao> cartao = cartaoRepository.findById(id);
        if(cartao.isEmpty()){
        	logger.warn("Cartão {} não encontrado=", id);
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado");
        }
        if(cartao.get().verificarSeCartaoEstaBloqueado()) {
        	logger.warn("Cartão {} já possui bloqueio=", id);
        	return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Cartão já possui bloqueio");
        }
        
        Bloqueio bloqueio = new Bloqueio(servletRequest.getLocalAddr(), 
        		servletRequest.getHeader("User-Agent"), cartao.get());
        bloquearCartao(bloqueio, cartao.get(), request);
        logger.info("Cartão bloqueado com sucesso");
		return ResponseEntity.ok().build();
	}
	
	private void bloquearCartao(Bloqueio bloqueio, Cartao cartao, BloqueioRequest request) {
		try {
			cartaoClient.bloqueioCartao(cartao.getNumero(), request);
			cartao.setBloqueio(bloqueio);
			executorTransacao.atualizaEComita(cartao);
		} catch (FeignException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Falha ao bloquear o cartão.");
		}
		
	}
}
