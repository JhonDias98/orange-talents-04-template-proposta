package br.com.zupacademy.jonathan.proposta.novaproposta;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.jonathan.proposta.cartao.Cartao;
import br.com.zupacademy.jonathan.proposta.utils.ofuscador.OfuscadorEncryptors;

@Entity
public class Proposta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	@Convert(converter = OfuscadorEncryptors.class)
	private String documento;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String nome;
	@NotBlank
	private String endereco;
	@NotNull
	@Positive
	private BigDecimal salario;
	@Enumerated(EnumType.STRING)
    private PropostaStatus status = PropostaStatus.NAO_ANALISADO;
	@OneToOne(cascade = CascadeType.MERGE)
	private Cartao cartao;
	
	public Proposta(@NotBlank String documento, @NotBlank @Email String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}
	
	public Proposta(String documento) {
		this.documento = documento;
	}

	@Deprecated
	public Proposta() {}

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

	public void colocarStatusDaAnalise(PropostaStatus status) {
		this.status = status;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

}
