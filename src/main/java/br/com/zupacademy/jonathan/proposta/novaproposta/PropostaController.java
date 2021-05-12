package br.com.zupacademy.jonathan.proposta.novaproposta;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.jonathan.proposta.gateway.analise.AnaliseClient;
import br.com.zupacademy.jonathan.proposta.gateway.analise.AnaliseClientRequest;
import br.com.zupacademy.jonathan.proposta.utils.ExecutorTransacao;
import feign.FeignException;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

	@Autowired
	private PropostaRepository repository;
	
	@Autowired
	private AnaliseClient analiseClient;
	
	@Autowired
	private ExecutorTransacao executorTransacao;
	
	private final Logger logger = LoggerFactory.getLogger(PropostaController.class);
	
	@GetMapping("/{id}")
	public ResponseEntity<PropostaResponse> consultarProposta(@PathVariable Long id) {
		Optional<Proposta> possivelProposta =  repository.findById(id);
		
		if(possivelProposta.isPresent()) {
			return ResponseEntity.ok(new PropostaResponse(possivelProposta.get()));
		}
        return possivelProposta.map(
                proposta -> ResponseEntity.ok(new PropostaResponse(proposta)))
                .orElseGet(() -> ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> criarProposta(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder uriBuilder) {
		
		if(repository.existsByDocumento(request.getDocumento())) {
			logger.warn("Já existe uma solicitação para o documento={}", request.getDocumento());
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Já existe uma solicitação para o documento informado");
		}
		
		Proposta proposta = request.toModel();
		
		executorTransacao.salvaEComita(proposta);
		consultaFinanceira(proposta);
		executorTransacao.atualizaEComita(proposta);
		
		logger.info("Proposta documento={} salário={} criada com sucesso!", proposta.getDocumento(), proposta.getSalario());
		URI uri = uriBuilder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
	}
	
	public void consultaFinanceira(Proposta proposta){
        PropostaStatus status;
        try{
            logger.info("Enviando proposta {} para analise financeira", proposta.getId());
            analiseClient.consulta(new AnaliseClientRequest(proposta));
            status = PropostaStatus.ELEGIVEL;
        }catch (FeignException.UnprocessableEntity e){
            logger.error("Proposta numero {} com restrição financeira.", proposta.getId());
            status = PropostaStatus.NAO_ELEGIVEL;
        }
        proposta.colocarStatusDaAnalise(status);
    }
}
