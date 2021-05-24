package br.com.zupacademy.robson.proposta.config.healthCheck;

import java.util.Map;
import java.util.HashMap;
import org.springframework.stereotype.Component;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

@Component
public class HealthCheck implements HealthIndicator {

    @Override
    public Health health() {
        Map<String, Object> details = new HashMap<>();
        details.put("versão", "1.2.3");
        details.put("descrição", "Health Check!");
        details.put("endereço", "127.0.0.1");
        return Health.up().withDetails(details).build();
    }
}
