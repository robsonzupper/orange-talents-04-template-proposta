package br.com.zupacademy.robson.proposta.models.proposal;

import br.com.zupacademy.robson.proposta.models.card.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.zupacademy.robson.proposta.utils.enums.ProposalStatusEnum;

import java.util.Set;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {

    Boolean existsProposalByDocument(String document);

    Set<Proposal> findProposalByStatusAndCard(ProposalStatusEnum status, Card card);
}
