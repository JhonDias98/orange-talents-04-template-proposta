package br.com.zupacademy.jonathan.proposta.gateway.analise;

public class AnaliseResponse {

	private String documento;
	private String nome;
	private AnaliseStatus resultadoAnalise;
	private Long idProposta;

	public AnaliseResponse(String documento, String nome, AnaliseStatus resultadoAnalise, Long idProposta) {
		this.documento = documento;
		this.nome = nome;
		this.resultadoAnalise = resultadoAnalise;
		this.idProposta = idProposta;
	}

	public String getDocumento() {
		return documento;
	}

	public String getNome() {
		return nome;
	}

	public AnaliseStatus getResultadoAnalise() {
		return resultadoAnalise;
	}

	public Long getIdProposta() {
		return idProposta;
	}

}
