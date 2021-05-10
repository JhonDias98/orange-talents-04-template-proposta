package br.com.zupacademy.jonathan.proposta.novaproposta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long>{

	boolean existsByDocumento(String documento);
	
	List<Proposta> findByStatusAndNumeroCartaoIsNull(PropostaStatus status);
}
