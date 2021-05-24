package br.com.zupacademy.robson.proposta.services.cardResourceService;

import br.com.zupacademy.robson.proposta.models.card.Card;
import br.com.zupacademy.robson.proposta.models.digitalWallet.DigitalWallet;

public class DigitalWalletFeignRequest {

    private String email;
    private String carteira;

    public DigitalWalletFeignRequest(String email, String carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public String getCarteira() {
        return carteira;
    }
}
