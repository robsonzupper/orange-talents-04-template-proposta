package br.com.zupacademy.robson.proposta.services.cardResourceService;

import java.time.LocalDateTime;
import br.com.zupacademy.robson.proposta.models.card.Card;

public class CardResourceResponse {

    private String id;
    private LocalDateTime emitidoEm;

    public CardResourceResponse(String id, LocalDateTime emitidoEm) {
        this.id = id;
        this.emitidoEm = emitidoEm;
    }

    public String getId() {
        return id;
    }

    public Card convert() {
        return new Card(id, emitidoEm);
    }
}
