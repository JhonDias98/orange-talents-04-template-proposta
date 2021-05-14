package br.com.zupacademy.jonathan.proposta.cartao;

import java.time.LocalDateTime;

import br.com.zupacademy.jonathan.proposta.bloqueio.BloqueioResponse;

public class CartaoResponse {

	private Long id;
    private String numero;
    private String titular;
    private LocalDateTime emitidoEm;
    private BloqueioResponse bloqueio;
    private CartaoStatus status;
    
    public CartaoResponse(Cartao cartao) {
		this.id = cartao.getId();
		this.numero = cartao.getNumero();
		this.titular = cartao.getTitular();
		this.emitidoEm = cartao.getEmitidoEm();
		this.status = cartao.getStatus();
		if(cartao.verificarSeCartaoEstaBloqueado()) {
			this.bloqueio = new BloqueioResponse(cartao.getBloqueio());
		}
	}

	public Long getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}

	public String getTitular() {
		return titular;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public BloqueioResponse getBloqueio() {
		return bloqueio;
	}

	public CartaoStatus getStatus() {
		return status;
	}
    
}
