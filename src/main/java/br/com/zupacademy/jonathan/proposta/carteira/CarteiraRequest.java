package br.com.zupacademy.jonathan.proposta.carteira;

public class CarteiraRequest {

	private String email;
	private String carteira;
	
	public CarteiraRequest(String email, String carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	public String getEmail() {
		return email;
	}

	public String getCarteira() {
		return carteira;
	}
	
}
