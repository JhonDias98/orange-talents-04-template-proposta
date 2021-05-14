package br.com.zupacademy.jonathan.proposta.novaproposta;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.jonathan.proposta.cartao.CartaoResponse;

public class PropostaResponse {

	private Long id;
	private String documento;
	private String email;
	private String nome;
	private String endereco;
	private BigDecimal salario;
	private PropostaStatus status;
	private CartaoResponse cartao;

	public PropostaResponse(@NotNull @Valid Proposta proposta) {
		this.id = proposta.getId();
		this.documento = proposta.getDocumento();
		this.email = proposta.getEmail();
		this.nome = proposta.getNome();
		this.endereco = proposta.getEndereco();
		this.salario = proposta.getSalario();
		this.status = proposta.getStatus();
		if(!(proposta.getCartao() == null)) {
			this.cartao = new CartaoResponse(proposta.getCartao());
		}
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

	public CartaoResponse getCartao() {
		return cartao;
	}
	
}
