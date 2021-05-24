package br.com.zupacademy.robson.proposta.models.digitalWallet;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import br.com.zupacademy.robson.proposta.models.card.Card;
import br.com.zupacademy.robson.proposta.utils.enums.DigitalWalletEnum;

public class DigitalWalletRequest {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Enumerated(EnumType.STRING)
    private DigitalWalletEnum carteira;

    public DigitalWalletRequest(String email, DigitalWalletEnum carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public DigitalWallet convert(Card card) {
        return new DigitalWallet(email, carteira, card);
    }

    public DigitalWalletEnum getCarteira() {
        return carteira;
    }
}
