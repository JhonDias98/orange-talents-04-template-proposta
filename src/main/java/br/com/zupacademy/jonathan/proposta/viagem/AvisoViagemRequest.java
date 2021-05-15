package br.com.zupacademy.jonathan.proposta.viagem;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.jonathan.proposta.cartao.Cartao;

public class AvisoViagemRequest {

	@NotBlank
	private String destino;
	@Future
	private LocalDate validoAte;
	
	public AvisoViagemRequest(@NotBlank String destino, @Future @NotNull LocalDate validoAte) {
		this.destino = destino;
		this.validoAte = validoAte;
	}
	
	public String getDestino() {
		return destino;
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}

	public AvisoViagem toModel(Cartao cartao, String ipCliente, String userAgent) {
        return new AvisoViagem(destino, validoAte, ipCliente, userAgent, cartao);
    }
		
}
