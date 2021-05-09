package br.com.zupacademy.jonathan.proposta.novaproposta.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "solicitacao", url = "${analises.host}")
public interface AnaliseClient {

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	AnaliseResponse consulta(AnaliseRequest request);
}
