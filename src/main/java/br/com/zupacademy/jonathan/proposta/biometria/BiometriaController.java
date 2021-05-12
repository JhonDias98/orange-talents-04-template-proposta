package br.com.zupacademy.jonathan.proposta.biometria;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.jonathan.proposta.cartao.Cartao;
import br.com.zupacademy.jonathan.proposta.cartao.CartaoRepository;
import br.com.zupacademy.jonathan.proposta.utils.ExecutorTransacao;

@RestController
@RequestMapping("/biometrias")
public class BiometriaController {

	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private ExecutorTransacao executorTransacao;
	
	private final Logger logger = LoggerFactory.getLogger(BiometriaController.class);
	
	@PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> salvarBiomatria(@PathVariable("id") Long id,
                                             @RequestBody @Valid BiometriaRequest request,
                                             UriComponentsBuilder uriBuilder  ){
		Optional<Cartao> cartao = cartaoRepository.findById(id);
		if(!cartao.isPresent()) {
			logger.warn("Cartão não encontrado");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(id);
		}
		
		Biometria biometria = request.toModel(cartao);
		executorTransacao.salvaEComita(biometria);
		logger.info("Biometria associada ao cartão {}", cartao.get().getId());
		URI uri = uriBuilder.path("/biometrias/{id}").build(biometria.getId());
		return ResponseEntity.created(uri).build();
	}
}
