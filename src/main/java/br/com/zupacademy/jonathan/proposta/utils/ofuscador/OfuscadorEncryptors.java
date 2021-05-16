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
		//return Encryptors.text("pass", "12345678").encrypt(documento);
		
		// queryableText é recomendando quando precisa de consultas
		return Encryptors.queryableText("pass", "12345678").encrypt(documento);
	}

	/**
	 * @param atributo será descompilado do banco de dados
	 * @return retorna ofuscado, ex: ******1-23
	 */
	@Override
	public String convertToEntityAttribute(String documento) {
		//String ofuscado = Encryptors.text("pass", "12345678").decrypt(documento);
		
		// queryableText é recomendando quando precisa de consultas
//		String ofuscado = Encryptors.queryableText("pass", "12345678").decrypt(documento);
//		return String.format("%10s", ofuscado.substring(ofuscado.length() - 4)).replace(' ', '*');
		return Encryptors.queryableText("pass", "12345678").decrypt(documento);
	}
	
}