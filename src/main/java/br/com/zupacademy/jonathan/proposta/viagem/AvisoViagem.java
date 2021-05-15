package br.com.zupacademy.jonathan.proposta.viagem;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import br.com.zupacademy.jonathan.proposta.cartao.Cartao;

@Entity
public class AvisoViagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String destino;
	@NotNull
	@CreationTimestamp
	private LocalDateTime instanteAvisoViagem = LocalDateTime.now();
	@NotNull
	private LocalDate validoAte;
	@NotBlank
	private String ipCliente;
	@NotBlank
	private String userAgent;
	@NotNull
	@ManyToOne()
	private Cartao cartao;
	
	public AvisoViagem(@NotBlank String destino, @NotNull LocalDate validoAte, @NotBlank String ipCliente,
			@NotBlank String userAgent, @NotNull Cartao cartao) {
		this.destino = destino;
		this.validoAte = validoAte;
		this.ipCliente = ipCliente;
		this.userAgent = userAgent;
		this.cartao = cartao;
	}

	@Deprecated
	public AvisoViagem() {}

	public Long getId() {
		return id;
	}

	public String getDestino() {
		return destino;
	}

	public LocalDateTime getInstanteAvisoViagem() {
		return instanteAvisoViagem;
	}

	public LocalDate getValidoAte() {
		return validoAte;
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
