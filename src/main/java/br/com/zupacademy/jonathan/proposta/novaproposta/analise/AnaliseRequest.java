package br.com.zupacademy.jonathan.proposta.novaproposta.analise;

import br.com.zupacademy.jonathan.proposta.novaproposta.Proposta;

public class AnaliseRequest {

	private String documento;
	private String nome;
	private Long id;

	public AnaliseRequest(Proposta proposta) {
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
