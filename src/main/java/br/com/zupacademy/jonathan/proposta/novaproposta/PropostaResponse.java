package br.com.zupacademy.jonathan.proposta.novaproposta;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PropostaResponse {

	private Long id;
	private String documento;
	private String email;
	private String nome;
	private String endereco;
	private BigDecimal salario;
	private PropostaStatus status;
	private String numeroCartao;

	public PropostaResponse(@NotNull @Valid Proposta proposta) {
		this.id = proposta.getId();
		this.documento = proposta.getDocumento();
		this.email = proposta.getEmail();
		this.nome = proposta.getEmail();
		this.endereco = proposta.getEndereco();
		this.salario = proposta.getSalario();
		this.status = proposta.getStatus();
		this.numeroCartao = proposta.getNumeroCartao();
	}

	public Long getId() {
		return id;
	}

	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public PropostaStatus getStatus() {
		return status;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}
	
}
