package br.com.zupacademy.robson.proposta.models.digitalWallet;

import java.net.URI;
import java.util.Optional;
import feign.FeignException;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import br.com.zupacademy.robson.proposta.models.card.Card;
import br.com.zupacademy.robson.proposta.models.card.CardRepository;
import br.com.zupacademy.robson.proposta.utils.transactions.TransactionExecutor;
import br.com.zupacademy.robson.proposta.services.cardResourceService.CardResourceClient;
import br.com.zupacademy.robson.proposta.services.cardResourceService.DigitalWalletFeignRequest;

@RestController
@RequestMapping("api/v1/carteiradigital")
public class DigitalWalletController {

    TransactionExecutor executor;
    CardResourceClient cardResourceClient;
    CardRepository cardRepository;
    DigitalWalletRepository digitalWalletRepository;

    public DigitalWalletController(TransactionExecutor executor,
            CardResourceClient cardResourceClient,
            CardRepository cardRepository,
            DigitalWalletRepository digitalWalletRepository) {
        this.executor = executor;
        this.cardResourceClient = cardResourceClient;
        this.cardRepository = cardRepository;
        this.digitalWalletRepository = digitalWalletRepository;
    }

    @PostMapping("{cardId}")
    public ResponseEntity<?> associateDigitalWalllet(@Valid @PathVariable("cardId") Long cardId,
            @RequestBody DigitalWalletRequest request,
            UriComponentsBuilder uriBuilder) {
        Optional<Card> card = cardRepository.findById(cardId);
        if (card.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else if (digitalWalletRepository.existsDigitalWalletByDigitalWalletAndCard(
                request.getCarteira(),
                card.get()
        )) {
            return ResponseEntity.unprocessableEntity().build();
        }
        DigitalWallet digitalWallet = request.convert(card.get());

        try {
            DigitalWalletFeignRequest digitalWalletFeignRequest = new DigitalWalletFeignRequest(
                    digitalWallet.getEmail(), digitalWallet.getDigitalWallet().toString()
            );
            cardResourceClient.associateDigitalWallet(card.get().getCardNumber(), digitalWalletFeignRequest);
            executor.saveAndCommit(digitalWallet);
            URI uri = uriBuilder.path("/carteiradigital/{id}").buildAndExpand(digitalWallet.getId()).toUri();
            return ResponseEntity.created(uri).body(new DigitalWalletResponse(digitalWallet));
        } catch (FeignException exception) {
            return ResponseEntity.unprocessableEntity().build();
        }

    }
}
