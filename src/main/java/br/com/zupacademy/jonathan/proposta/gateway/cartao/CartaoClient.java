package br.com.zupacademy.jonathan.proposta.gateway.cartao;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.zupacademy.jonathan.proposta.bloqueio.BloqueioRequest;
import br.com.zupacademy.jonathan.proposta.carteira.CarteiraRequest;
import br.com.zupacademy.jonathan.proposta.carteira.CarteiraResponse;
//import br.com.zupacademy.jonathan.proposta.gateway.cartao.bloqueio.BloqueioClientResponse;
import br.com.zupacademy.jonathan.proposta.viagem.AvisoViagemRequest;

@FeignClient(value = "cartoes", url = "${cartoes.host}")
@Component
public interface CartaoClient {

    @GetMapping
    public CartaoClientResponse consultaCartao(@RequestParam("idProposta") Long idProposta);

    @PostMapping("/{id}/bloqueios") 
    public void bloqueioCartao(@PathVariable String id, @RequestBody @Valid BloqueioRequest request);
    
    @PostMapping("/{id}/avisos") 
    public void avisarViagem(@PathVariable String id, @RequestBody @Valid AvisoViagemRequest request);
    
    @PostMapping("/{id}/carteiras") 
    public CarteiraResponse associarCarteira(@PathVariable String id, @RequestBody @Valid CarteiraRequest request);
}