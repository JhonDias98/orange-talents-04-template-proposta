package br.com.zupacademy.jonathan.proposta.carteira;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CarteiraTipo {
	PAYPAL,
    SAMSUNG_PLAY;
	
	@JsonCreator
	public static CarteiraTipo verificador(String tipo) {
        for (var tipoCarteira : values()) {
            if (tipo.equalsIgnoreCase(tipoCarteira.toString())) {
                return tipoCarteira;
            }
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo da carteira informado não é válido");
    }
	
}
