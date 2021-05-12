package br.com.zupacademy.jonathan.proposta.biometria;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zupacademy.jonathan.proposta.cartao.Cartao;

@Entity
public class Biometria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private byte[] biometria;
	@NotNull
	@ManyToOne
	private Cartao cartao;
	@NotNull
	@CreationTimestamp
	private LocalDateTime dataAssociacao = LocalDateTime.now();
	
	public Biometria(@NotNull byte[] biometria, Cartao cartao) {
		this.biometria = biometria;
		this.cartao = cartao;
	}

	@Deprecated
	public Biometria() {}

	public Long getId() {
		return id;
	}

	public byte[] getBiometria() {
		return biometria;
	}

	public Cartao getCartao() {
		return cartao;
	}

}
