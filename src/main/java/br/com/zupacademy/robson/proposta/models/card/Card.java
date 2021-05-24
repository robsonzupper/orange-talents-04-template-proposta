package br.com.zupacademy.robson.proposta.models.card;

import javax.persistence.Id;
import java.time.LocalDateTime;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Convert;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.UniqueConstraint;
import javax.persistence.SequenceGenerator;
import br.com.zupacademy.robson.proposta.utils.cryptography.DataConverter;

@Entity
@Table(
        uniqueConstraints = {
            @UniqueConstraint(name = "card_number_unique", columnNames = "cardNumber")
        }
)
public class Card {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "card_sequence"
    )
    @SequenceGenerator(
            name = "card_sequence",
            sequenceName = "card_sequence",
            allocationSize = 1
    )
    private Long id;

    @Column(nullable = false)
    @Convert(converter = DataConverter.class)
    private String cardNumber;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Card(String cardNumber, LocalDateTime createdAt) {
        this.cardNumber = cardNumber;
        this.createdAt = createdAt;
    }

    @Deprecated
    public Card() {
    }

    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
