package br.com.zupacademy.jonathan.proposta.gateway.cartao;

import java.time.LocalDateTime;

import br.com.zupacademy.jonathan.proposta.cartao.Cartao;
import br.com.zupacademy.jonathan.proposta.novaproposta.Proposta;

public class CartaoClientResponse {
	
	private String id;
	private String titular;
	private LocalDateTime emitidoEm;
	private String idProposta;

	public CartaoClientResponse(String id, String titular, LocalDateTime emitidoEm, String idProposta) {
		this.id = id;
		this.titular = titular;
		this.emitidoEm = emitidoEm;
		this.idProposta = idProposta;
	}

	public String getId() {
		return id;
	}

	public String getTitular() {
		return titular;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getIdProposta() {
		return idProposta;
	}
	
	public Cartao toModel(Proposta proposta) {
		return new Cartao(id, titular, emitidoEm, proposta);
	}
}
