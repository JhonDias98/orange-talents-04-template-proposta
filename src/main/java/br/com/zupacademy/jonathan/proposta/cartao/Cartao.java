package br.com.zupacademy.jonathan.proposta.cartao;

public class Cartao {

	private String id;

    private String titular;

    private String idProposta;

    @Deprecated
    public Cartao() {}

    public Cartao(String id, String titular, String idProposta) {
        this.id = id;
        this.titular = titular;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }

    public String getTitular() {
        return titular;
    }

    public String getIdProposta() {
        return idProposta;
    }
	
}
