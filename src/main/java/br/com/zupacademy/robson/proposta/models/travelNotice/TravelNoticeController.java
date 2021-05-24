package br.com.zupacademy.robson.proposta.models.travelNotice;

import java.util.Optional;
import feign.FeignException;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.zupacademy.robson.proposta.models.card.Card;
import br.com.zupacademy.robson.proposta.models.card.CardRepository;
import br.com.zupacademy.robson.proposta.utils.transactions.TransactionExecutor;
import br.com.zupacademy.robson.proposta.services.cardResourceService.CardResourceClient;
import br.com.zupacademy.robson.proposta.services.cardResourceService.NoticeTravelRequest;

@RestController
@RequestMapping("api/v1/avisoviagem")
public class TravelNoticeController {

    private final TravelNoticeRepository travelNoticeRepository;
    private final CardRepository cardRepository;
    private final TransactionExecutor executor;
    private final CardResourceClient cardResourceClient;

    public TravelNoticeController(TravelNoticeRepository travelNoticeRepository,
            CardRepository cardRepository, TransactionExecutor executor,
            CardResourceClient cardResourceClient) {

        this.travelNoticeRepository = travelNoticeRepository;
        this.cardRepository = cardRepository;
        this.executor = executor;
        this.cardResourceClient = cardResourceClient;
    }

    @PostMapping("{id}")
    public ResponseEntity<?> noticeTravel(@PathVariable("id") Long cardId,
            @Valid @RequestBody TravelNoticeRequest request,
            HttpServletRequest httpRequest) {
        Optional<Card> card = cardRepository.findById(cardId);
        if (card.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        try {
            cardResourceClient.noticeTravel(card.get().getCardNumber().toString(),
                    new NoticeTravelRequest(request.getDestination(), request.getArrivalDate()));

            String ipAddress = httpRequest.getRemoteAddr();
            String userAgent = httpRequest.getHeader("User-Agent");

            TravelNotice travelNotice = new TravelNotice(
                    request.getDestination(),
                    request.getArrivalDate(),
                    ipAddress,
                    userAgent,
                    card.get()
            );

            executor.saveAndCommit(travelNotice);
            return ResponseEntity.ok().build();
        } catch (FeignException exception) {
            return ResponseEntity.unprocessableEntity().build();
        }
    }
}
