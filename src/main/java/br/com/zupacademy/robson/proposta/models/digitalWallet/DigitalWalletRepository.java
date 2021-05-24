package br.com.zupacademy.robson.proposta.models.digitalWallet;

import br.com.zupacademy.robson.proposta.models.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.zupacademy.robson.proposta.utils.enums.DigitalWalletEnum;

public interface DigitalWalletRepository extends JpaRepository<DigitalWallet, Long> {

    Boolean existsDigitalWalletByDigitalWalletAndCard(DigitalWalletEnum digitalWallet,
            Card card);
}
