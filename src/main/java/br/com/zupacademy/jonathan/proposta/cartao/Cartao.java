package br.com.zupacademy.jonathan.proposta.cartao;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.jonathan.proposta.biometria.Biometria;
import br.com.zupacademy.jonathan.proposta.bloqueio.Bloqueio;
import br.com.zupacademy.jonathan.proposta.novaproposta.Proposta;
import br.com.zupacademy.jonathan.proposta.viagem.AvisoViagem;

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
    private LocalDateTime emitidoEm;
	@NotNull
	@OneToOne
    private Proposta proposta;
	@OneToMany(mappedBy = "cartao")
    private Set<Biometria> biometrias;
	@OneToOne(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private Bloqueio bloqueio;
    @Enumerated(value = EnumType.STRING)
    private CartaoStatus status = CartaoStatus.ATIVO;
    @OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private Set<AvisoViagem> viagens;
	
	public Cartao(@NotBlank String numero, @NotBlank String titular, @NotNull LocalDateTime emitidoEm,
			@NotNull Proposta proposta) {
		this.numero = numero;
		this.titular = titular;
		this.emitidoEm = emitidoEm;
		this.proposta = proposta;
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

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public Proposta getProposta() {
		return proposta;
	}

	public Set<Biometria> getBiometrias() {
		return biometrias;
	}

	public Bloqueio getBloqueio() {
		return bloqueio;
	}

	public CartaoStatus getStatus() {
		return status;
	}
	
	public Set<AvisoViagem> getViagens() {
		return viagens;
	}

	// métodos abaixo são usados para as regras de negócios

	public boolean verificarSeCartaoEstaBloqueado() {
        return this.status.equals(CartaoStatus.BLOQUEADO);
    }

    public void setBloqueio(Bloqueio bloqueio) {
        this.bloqueio = bloqueio;
        this.status = CartaoStatus.BLOQUEADO;
    }
    
    public void setViagem(AvisoViagem viagem) {
		this.viagens.add(viagem);
		this.status = CartaoStatus.EM_VIAGEM;
	}
	
}
