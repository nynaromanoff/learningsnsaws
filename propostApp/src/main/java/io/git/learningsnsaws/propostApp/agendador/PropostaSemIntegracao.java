package io.git.learningsnsaws.propostApp.agendador;

import io.git.learningsnsaws.propostApp.entity.Proposta;
import io.git.learningsnsaws.propostApp.repository.PropostaRepository;
import io.git.learningsnsaws.propostApp.service.NotificationRabbitMQService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class PropostaSemIntegracao {

    private final PropostaRepository repository;

    private final NotificationRabbitMQService notificationRabbitMQService;

    private final String exchange;

    private final Logger logger = LoggerFactory.getLogger(PropostaSemIntegracao.class);

    public PropostaSemIntegracao(PropostaRepository repository,
                                 @Value("${rabbitmq.propostapendente.exchange}") String exchange,
                                 NotificationRabbitMQService notificationRabbitMQService) {
        this.repository = repository;
        this.exchange = exchange;
        this.notificationRabbitMQService = notificationRabbitMQService;
    }

    @Scheduled(fixedDelay = 10, timeUnit = TimeUnit.SECONDS)
    public void buscarPropostaSemIntegracao() {
        repository.findAllByIntegradaIsFalse().forEach(proposta -> {
            try {
                notificationRabbitMQService.notify(proposta,exchange);
                updateProposta(proposta);
            } catch (RuntimeException ex) {
                logger.error(ex.getMessage());
            }
        });
    }

    private void updateProposta(Proposta proposta) {
        proposta.setIntegrada(true);
        repository.save(proposta);
    }
}
