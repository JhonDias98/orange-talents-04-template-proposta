package br.com.zupacademy.jonathan.proposta.carteira;

import br.com.zupacademy.jonathan.proposta.cartao.Cartao;

public class CarteiraResponse {

	private String resultado;

    private String id;

    public CarteiraResponse(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
    
    public Carteira toModel(CarteiraRequest request, Cartao cartao) {
    	return new Carteira(id, request.getEmail(), request.getCarteira(), cartao);
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return "Resultado: " + resultado + " , id: " + id;
    }
}
