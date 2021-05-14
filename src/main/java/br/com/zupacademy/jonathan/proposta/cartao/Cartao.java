package br.com.zupacademy.jonathan.proposta.cartao;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.jonathan.proposta.bloqueio.Bloqueio;

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
	@OneToOne(mappedBy = "cartao", cascade = CascadeType.MERGE)
    private Bloqueio bloqueio;
    @Enumerated(value = EnumType.STRING)
    private CartaoStatus status = CartaoStatus.ATIVO;
	
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

	public Bloqueio getBloqueio() {
		return bloqueio;
	}

	public CartaoStatus getStatus() {
		return status;
	}
	
	public boolean verificarSeCartaoEstaBloqueado(){
        return this.status.equals(CartaoStatus.BLOQUEADO);
    }

    public void setBloqueio(Bloqueio bloqueio) {
        this.bloqueio = bloqueio;
        this.status = CartaoStatus.BLOQUEADO;
    }
	
}
