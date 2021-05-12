package br.com.zupacademy.jonathan.proposta.utils.ofuscador;

import java.util.Base64;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *Não recomendado para dados sensíveis, criada apenas para teste
 */
@Converter
public class OfuscadorBase64 implements AttributeConverter<String, String>{

	/**
	 * @param atributo será ofuscado no banco de dados
	 * @return documento criptografado para o banco de dados
	 */
	@Override
	public String convertToDatabaseColumn(String documento) {
		return Base64.getEncoder().encodeToString(documento.getBytes());
	}

	/**
	 * @param atributo será descompilado do banco de dados
	 * @return retorna ofuscado, ex: ******1-23
	 */
	@Override
	public String convertToEntityAttribute(String documento) {
		byte[] decode = Base64.getDecoder().decode(documento.getBytes());
		String campoDecodificada = new String(decode);
		return String.format("%10s", campoDecodificada.substring(campoDecodificada.length() - 4)).replace(' ', '*');
	}
	
}