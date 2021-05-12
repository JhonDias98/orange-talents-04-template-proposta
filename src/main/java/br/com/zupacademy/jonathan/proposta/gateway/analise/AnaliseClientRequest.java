package br.com.zupacademy.jonathan.proposta.gateway.analise;

import br.com.zupacademy.jonathan.proposta.novaproposta.Proposta;

public class AnaliseClientRequest {

	private String documento;
	private String nome;
	private Long id;

	public AnaliseClientRequest(Proposta proposta) {
		this.documento = proposta.getDocumento();
		this.nome = proposta.getNome();
		this.id = proposta.getId();
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public Long getId() {
		return id;
	}

}
