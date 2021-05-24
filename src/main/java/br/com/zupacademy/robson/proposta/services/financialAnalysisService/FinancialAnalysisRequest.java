package br.com.zupacademy.robson.proposta.services.financialAnalysisService;

import br.com.zupacademy.robson.proposta.models.proposal.Proposal;

public class FinancialAnalysisRequest {
    private String documento;
    private String nome;
    private String idProposta;

    public FinancialAnalysisRequest(Proposal proposal) {
        this.documento = proposal.getDocument();
        this.nome = proposal.getName();
        this.idProposta = proposal.getId().toString();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
