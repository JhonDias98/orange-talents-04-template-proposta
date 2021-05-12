package br.com.zupacademy.jonathan.proposta.gateway.cartao;

import br.com.zupacademy.jonathan.proposta.cartao.Cartao;

public class CartaoClientResponse {
	
	private String id;
	private String titular;
	private String idProposta;

	public CartaoClientResponse(String id, String titular, String idProposta) {
		this.id = id;
		this.titular = titular;
		this.idProposta = idProposta;
	}

	public String getId() {
		return id;
	}

	public String getTitular() {
		return titular;
	}

	public String getIdProposta() {
		return idProposta;
	}
	
	public Cartao toModel() {
		return new Cartao(id, titular, idProposta);
	}
}
