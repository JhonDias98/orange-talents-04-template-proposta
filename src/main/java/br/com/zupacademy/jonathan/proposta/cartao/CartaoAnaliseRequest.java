package br.com.zupacademy.jonathan.proposta.cartao;

public class CartaoAnaliseRequest {

	private String documento;
	private String nome;
	private String idProposta;

	public CartaoAnaliseRequest(String documento, String nome, String idProposta) {
		this.documento = documento;
		this.nome = nome;
		this.idProposta = idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public String getIdProposta() {
		return idProposta;
	}
}
