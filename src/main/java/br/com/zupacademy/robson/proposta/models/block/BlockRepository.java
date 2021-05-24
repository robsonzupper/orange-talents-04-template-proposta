package br.com.zupacademy.robson.proposta.models.block;

import br.com.zupacademy.robson.proposta.models.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block, Long> {
    Boolean existsBlockByCard(Card card);
}
