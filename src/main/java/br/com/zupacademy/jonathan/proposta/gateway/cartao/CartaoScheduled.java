package br.com.zupacademy.jonathan.proposta.gateway.cartao;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zupacademy.jonathan.proposta.cartao.Cartao;
import br.com.zupacademy.jonathan.proposta.novaproposta.Proposta;
import br.com.zupacademy.jonathan.proposta.novaproposta.PropostaRepository;
import br.com.zupacademy.jonathan.proposta.novaproposta.PropostaStatus;
import feign.FeignException;

@Component
public class CartaoScheduled {

	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private CartaoClient cartaoClient;
	
	private final Logger logger = LoggerFactory.getLogger(CartaoScheduled.class);
	
	@Scheduled(fixedDelayString = "${periodicidade.executa-operacao}")
	@Transactional
    private void executaOperacao() {
		List<Proposta> propostas = propostaRepository.findByStatusAndCartaoIsNull(PropostaStatus.ELEGIVEL);
		
		if(propostas.isEmpty()) {
			logger.info("Não temos propostas elegíveis");
		} else {
			logger.info("temos {} propostas elegíveis", propostas.size());
		}
		
		try {
			for(Proposta proposta : propostas) {
				CartaoClientResponse cartaoAnalise = cartaoClient.consultaCartao(proposta.getId());
				Cartao novoCartao = cartaoAnalise.toModel();
				proposta.setCartao(novoCartao);
				propostaRepository.save(proposta);
				logger.info("Cartão criado para a proposta={}", proposta.getId());
			}
		} catch (FeignException e){
			logger.error("Cartão ainda não está disponível. Erro={}", e.toString());
		} catch (Exception e) {
			e.printStackTrace();
	     }		
    }
}
 