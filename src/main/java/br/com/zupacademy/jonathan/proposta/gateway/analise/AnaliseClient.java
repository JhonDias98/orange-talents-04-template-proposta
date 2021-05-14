package br.com.zupacademy.jonathan.proposta.gateway.analise;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "solicitacao", url = "${analises.host}")
public interface AnaliseClient {

	@PostMapping
	AnaliseClientResponse consulta(AnaliseClientRequest request);
}
