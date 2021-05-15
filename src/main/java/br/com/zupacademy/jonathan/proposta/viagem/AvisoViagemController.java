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
import br.com.zupacademy.jonathan.proposta.utils.ExecutorTransacao;

@RestController
@RequestMapping("/viagens")
public class AvisoViagemController {
	
	@Autowired
    private CartaoRepository cartaoRepository;
	@Autowired
	private ExecutorTransacao executorTransacao;
	
	private final Logger logger = LoggerFactory.getLogger(AvisoViagemController.class);

	@PostMapping("/{idCartao}")
	public ResponseEntity<?> bloquearCartao(@PathVariable("idCartao") Long id,
												HttpServletRequest servletRequest,
												@RequestBody @Valid AvisoViagemRequest request){
		
		Optional<Cartao> cartao = cartaoRepository.findById(id);
        if(cartao.isEmpty()){
        	logger.warn("Cartão {} não encontrado=", id);
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cartão não encontrado");
        }
        
        AvisoViagem novoAvisoViagem = request.toModel(cartao.get(), servletRequest.getLocalAddr(), 
        		servletRequest.getHeader("User-Agent"));
        cartao.get().setViagem(novoAvisoViagem);
        executorTransacao.atualizaEComita(cartao.get());
        logger.info("Cartão bloqueado com sucesso");
		return ResponseEntity.ok().build();
	}
	
}
