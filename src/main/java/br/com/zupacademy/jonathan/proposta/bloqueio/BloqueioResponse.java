package br.com.zupacademy.jonathan.proposta.bloqueio;

import java.time.LocalDateTime;

public class BloqueioResponse {

	private Long id;
    private LocalDateTime instanteBloqueio;
    
    public BloqueioResponse(Bloqueio bloqueio) {
		this.id = bloqueio.getId();
		this.instanteBloqueio = bloqueio.getInstanteBloqueio();
	}

	public Long getId() {
		return id;
	}

	public LocalDateTime getInstanteBloqueio() {
		return instanteBloqueio;
	}
    
}
