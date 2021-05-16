package br.com.zupacademy.jonathan.proposta.utils.ofuscador;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.security.crypto.encrypt.Encryptors;

@Converter
public class OfuscadorEncryptors implements AttributeConverter<String, String>{

	/**
	 * @param atributo será ofuscado no banco de dados
	 * @return documento criptografado para o banco de dados
	 */
	@Override
	public String convertToDatabaseColumn(String documento) {
		try {
           return Encryptors.queryableText("${proposta.ofuscar.dados}", "12345678").encrypt(documento);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}

	/**
	 * @param atributo será descompilado do banco de dados
	 * @return retorna ofuscado, ex: ******1-23
	 */
	@Override
	public String convertToEntityAttribute(String documento) {
		try {
           return Encryptors.queryableText("${proposta.ofuscar.dados}", "12345678").decrypt(documento);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }	
	}
	
}