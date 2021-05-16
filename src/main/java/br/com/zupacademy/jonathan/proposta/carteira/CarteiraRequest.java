package br.com.zupacademy.jonathan.proposta.carteira;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraRequest {

	@NotBlank
	private String email;
	@NotNull
	private CarteiraTipo carteira;
	
	public CarteiraRequest(String email, CarteiraTipo carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	public String getEmail() {
		return email;
	}

	public CarteiraTipo getCarteira() {
		return carteira;
	}
	
}
