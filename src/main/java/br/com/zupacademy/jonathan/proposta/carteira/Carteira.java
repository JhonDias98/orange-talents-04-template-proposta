package br.com.zupacademy.jonathan.proposta.carteira;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.jonathan.proposta.cartao.Cartao;

@Entity
public class Carteira {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String idAssociacao;
	@NotBlank
	private String email;
	@NotNull
	@Enumerated(EnumType.STRING)
	private CarteiraTipo carteira;
	@ManyToOne
	private Cartao cartao;
	
	public Carteira(@NotBlank String idAssociacao, @NotBlank String email, @NotNull CarteiraTipo carteira, Cartao cartao) {
		this.idAssociacao = idAssociacao;
		this.email = email;
		this.carteira = carteira;
		this.cartao = cartao;
	}

	public Long getId() {
		return id;
	}

	public String getIdAssociacao() {
		return idAssociacao;
	}

	public String getEmail() {
		return email;
	}

	public CarteiraTipo getCarteira() {
		return carteira;
	}

	public Cartao getCartao() {
		return cartao;
	}
	
}
