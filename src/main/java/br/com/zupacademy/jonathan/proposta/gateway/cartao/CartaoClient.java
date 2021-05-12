package br.com.zupacademy.jonathan.proposta.gateway.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cartoes", url = "${cartoes.host}")
@Component
public interface CartaoClient {

    @RequestMapping(method = RequestMethod.GET,  produces = "application/json")
    public CartaoClientResponse consultaCartao(@RequestParam("idProposta") Long idProposta);

}