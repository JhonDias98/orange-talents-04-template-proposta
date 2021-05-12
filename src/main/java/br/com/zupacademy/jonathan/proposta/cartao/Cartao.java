package br.com.zupacademy.jonathan.proposta.cartao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String numero;
	@NotBlank
	private String titular;
	@NotNull
	private String idProposta;
	
	public Cartao(@NotBlank String numero, @NotBlank String titular, @NotNull String idProposta) {
		this.numero = numero;
		this.titular = titular;
		this.idProposta = idProposta;
	}
	
	@Deprecated
	public Cartao() {}

	public Long getId() {
		return id;
	}

	public String getNumero() {
		return numero;
	}

	public String getTitular() {
		return titular;
	}

	public String getIdProposta() {
		return idProposta;
	}
	
}
