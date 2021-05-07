package br.com.zupacademy.jonathan.proposta.novaproposta;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

	@Autowired
	private PropostaRepository repository;
	
	private final Logger logger = LoggerFactory.getLogger(PropostaController.class);
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> criarProposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uriBuilder) {
		
		if(repository.existsByDocumento(request.getDocumento())) {
			logger.warn("Já existe uma solicitação para o documento={}", request.getDocumento());
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Já existe uma solicitação para o documento informado");
		}
		
		Proposta proposta = request.toModel();
		repository.save(proposta);
		logger.info("Proposta documento={} salário={} criada com sucesso!", proposta.getDocumento(), proposta.getSalario());
		
		URI uri = uriBuilder.path("propostas/{id}").build(proposta.getId());
        return ResponseEntity.created(uri).build();
	}
}
