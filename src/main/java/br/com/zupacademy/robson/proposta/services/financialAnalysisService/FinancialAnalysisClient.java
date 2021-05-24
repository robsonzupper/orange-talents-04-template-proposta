package br.com.zupacademy.robson.proposta.services.financialAnalysisService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "financialAnalysis", url = "${client.financialAnalysis.url}")
public interface FinancialAnalysisClient {

    @PostMapping
    FinancialAnalysisResponse financialAnalysis(FinancialAnalysisRequest financialAnalysisRequest);
}
