package br.com.zupacademy.jonathan.proposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "cartoes", url = "${cartoes.host}")
public interface CartaoClient {
    
    @RequestMapping(method = RequestMethod.POST,  produces = "application/json")
    public Cartao gerarCartao(@RequestBody CartaoAnaliseRequest dadosDoSolicitante);

}