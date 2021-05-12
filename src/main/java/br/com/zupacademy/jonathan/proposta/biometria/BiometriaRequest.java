package br.com.zupacademy.jonathan.proposta.biometria;

import java.util.Base64;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import br.com.zupacademy.jonathan.proposta.cartao.Cartao;

public class BiometriaRequest {

	@NotNull
	private String biometria;

	public String getBiometria() {
		return biometria;
	}
	
	public Biometria toModel(Optional<Cartao> cartao) {
		byte[] biometriaGerada = Base64.getEncoder().encode(biometria.getBytes());
		return new Biometria(biometriaGerada, cartao.get());
	}
	
}
