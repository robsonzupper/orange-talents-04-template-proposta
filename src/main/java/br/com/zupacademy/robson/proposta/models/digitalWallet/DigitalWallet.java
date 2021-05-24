package br.com.zupacademy.robson.proposta.models.digitalWallet;

import br.com.zupacademy.robson.proposta.models.card.Card;
import br.com.zupacademy.robson.proposta.utils.enums.DigitalWalletEnum;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DigitalWallet {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "digital_wallet_sequence"
    )
    @SequenceGenerator(
            sequenceName = "digital_wallet_sequence",
            name = "digital_wallet_sequence",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DigitalWalletEnum digitalWallet;

    @Column(nullable = false)
    private LocalDateTime associatedAt = LocalDateTime.now();

    @ManyToOne
    private Card card;

    public DigitalWallet(String email, DigitalWalletEnum digitalWallet, Card card) {
        this.email = email;
        this.digitalWallet = digitalWallet;
        this.card = card;
    }

    public DigitalWallet() {
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getAssociatedAt() {
        return associatedAt;
    }

    public Card getCard() {
        return card;
    }

    public String getEmail() {
        return email;
    }

    public DigitalWalletEnum getDigitalWallet() {
        return digitalWallet;
    }
}
