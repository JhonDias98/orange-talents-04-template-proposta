package br.com.zupacademy.jonathan.proposta.carteira;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.jonathan.proposta.cartao.Cartao;

public interface CarteiraRepository extends JpaRepository<Carteira, Long>{

	boolean existsByCartaoAndCarteira(Cartao cartao, String carteira);
}
