package br.com.zupacademy.robson.proposta.models.block;

import java.util.Optional;
import feign.FeignException;
import javax.transaction.Transactional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.zupacademy.robson.proposta.models.card.Card;
import br.com.zupacademy.robson.proposta.models.card.CardRepository;
import br.com.zupacademy.robson.proposta.utils.transactions.TransactionExecutor;
import br.com.zupacademy.robson.proposta.services.cardResourceService.BlockCardRequest;
import br.com.zupacademy.robson.proposta.services.cardResourceService.BlockCardResponse;
import br.com.zupacademy.robson.proposta.services.cardResourceService.CardResourceClient;

@RestController
@RequestMapping("api/v1/bloqueio")
public class BlockController {

    private final BlockRepository blockRepository;

    private final CardRepository cardRepository;

    private final CardResourceClient cardResourceClient;

    private final TransactionExecutor executor;

    public BlockController(BlockRepository blockRepository,
            CardRepository cardRepository, CardResourceClient cardResourceClient,
            TransactionExecutor executor) {

        this.blockRepository = blockRepository;
        this.cardRepository = cardRepository;
        this.cardResourceClient = cardResourceClient;
        this.executor = executor;
    }

    @PostMapping("/{cardId}")
    public ResponseEntity<?> blockCard(@PathVariable Long cardId,
            HttpServletRequest request) {

        Optional<Card> card = cardRepository.findById(cardId);
        if (card.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else if (blockRepository.existsBlockByCard(card.get())) {
            return ResponseEntity.unprocessableEntity().build();
        }

        String ipAddress = request.getRemoteAddr();
        String userAgent = request.getHeader("User-Agent");

        Block blockCard = new Block(ipAddress, userAgent, card.get());

        try {
            cardResourceClient.blockCard(card.get().getCardNumber(),
                    new BlockCardRequest());

            executor.saveAndCommit(blockCard);
            return ResponseEntity.ok().build();
        } catch (FeignException exception) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}
