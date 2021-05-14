package br.com.zupacademy.jonathan.proposta.bloqueio;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zupacademy.jonathan.proposta.cartao.Cartao;

@Entity
public class Bloqueio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@CreationTimestamp
	private LocalDateTime instanteBloqueio = LocalDateTime.now();
	@NotBlank
	private String ipCliente;
	@NotBlank
	private String userAgent;
	@NotNull
	@OneToOne
	private Cartao cartao;
	
	public Bloqueio(@NotBlank String ipCliente, @NotBlank String userAgent, @NotNull Cartao cartao) {
		this.ipCliente = ipCliente;
		this.userAgent = userAgent;
		this.cartao = cartao;
	}

	@Deprecated
	public Bloqueio() {}

	public Long getId() {
		return id;
	}

	public LocalDateTime getInstanteBloqueio() {
		return instanteBloqueio;
	}

	public String getIpCliente() {
		return ipCliente;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public Cartao getCartao() {
		return cartao;
	}

}
