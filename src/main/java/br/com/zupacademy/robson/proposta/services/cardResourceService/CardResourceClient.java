package br.com.zupacademy.robson.proposta.services.cardResourceService;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cardResource", url = "${client.cardResource.url}")
public interface CardResourceClient {
    @GetMapping
    CardResourceResponse getCard(@RequestParam("idProposta") String idProposal);

    @PostMapping("/{id}/bloqueios")
    BlockCardResponse blockCard(@PathVariable("id") String cardId, BlockCardRequest request);

    @PostMapping("{id}/avisos")
    NoticeTravelResponse noticeTravel(@PathVariable("id") String cardId, NoticeTravelRequest request);

    @PostMapping("{id}/carteiras")
    void associateDigitalWallet(@PathVariable("id") String cardId, DigitalWalletFeignRequest request);

}
