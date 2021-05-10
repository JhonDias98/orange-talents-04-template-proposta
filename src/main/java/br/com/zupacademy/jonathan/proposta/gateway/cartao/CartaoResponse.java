package br.com.zupacademy.jonathan.proposta.gateway.cartao;

public class CartaoResponse {
	
	private String id;
	private String titular;
	private String idProposta;

	public CartaoResponse(String id, String titular, String idProposta) {
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
}
