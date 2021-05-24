package br.com.zupacademy.robson.proposta.services.cardResourceService;

import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.stereotype.Component;
import br.com.zupacademy.robson.proposta.models.card.Card;
import org.springframework.scheduling.annotation.Scheduled;
import br.com.zupacademy.robson.proposta.models.proposal.Proposal;
import br.com.zupacademy.robson.proposta.utils.enums.ProposalStatusEnum;
import br.com.zupacademy.robson.proposta.models.proposal.ProposalRepository;
import br.com.zupacademy.robson.proposta.utils.transactions.TransactionExecutor;

import java.util.Set;

@Component
public class CardResourceScheduler {

    private final CardResourceClient cardResourceClient;
    private final TransactionExecutor transaction;
    private final ProposalRepository proposalRepository;
    private final Tracer tracer;

    public CardResourceScheduler(CardResourceClient cardResourceClient,
            TransactionExecutor transaction, ProposalRepository proposalRepository,
            Tracer tracer) {

        this.cardResourceClient = cardResourceClient;
        this.transaction = transaction;
        this.proposalRepository = proposalRepository;
        this.tracer = tracer;
    }

    @Scheduled(fixedDelayString = "${scheduler.card.resource}")
    private void getCard() {
        Set<Proposal> proposals = proposalRepository.findProposalByStatusAndCard(ProposalStatusEnum.ELEGIVEL, null);

        proposals.forEach(proposal -> {

            CardResourceResponse cardResponse = cardResourceClient.getCard(proposal.getId().toString());
            Card card = cardResponse.convert();
            transaction.saveAndCommit(card);

            proposal.setCard(card);
            transaction.updateAndCommit(proposal);
        });

    }
}
