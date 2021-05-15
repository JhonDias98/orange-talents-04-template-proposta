package br.com.zupacademy.jonathan.proposta.viagem;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AvisoViagemResponse {

	private String destino;
    private LocalDateTime instanteAvisoViagem;
    private LocalDate validoAte;
    
    public AvisoViagemResponse(AvisoViagem aviso) {
		this.destino = aviso.getDestino();
		this.instanteAvisoViagem = aviso.getInstanteAvisoViagem();
		this.validoAte = aviso.getValidoAte();
	}

	public String getDestino() {
		return destino;
	}

	public LocalDateTime getInstanteAvisoViagem() {
		return instanteAvisoViagem;
	}

	public LocalDate getValidoAte() {
		return validoAte;
	}
 
}
