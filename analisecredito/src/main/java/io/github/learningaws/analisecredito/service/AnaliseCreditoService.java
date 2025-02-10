package io.github.learningaws.analisecredito.service;

import io.github.learningaws.analisecredito.domain.Proposta;
import io.github.learningaws.analisecredito.exceptions.StrategyException;
import io.github.learningaws.analisecredito.service.strategy.CalculoPonto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaliseCreditoService {
    private List<CalculoPonto> calculoPontoList;

    private NotificationRabbitMQService notificationRabbitMQService;

    private String exchange;

    public AnaliseCreditoService(NotificationRabbitMQService notificationRabbitMQService,
                                 @Value("${rabbitmq.propostaconcluida.exchange}") String exchange,
                                 List<CalculoPonto> calculoPontoList) {
        this.notificationRabbitMQService = notificationRabbitMQService;
        this.exchange = exchange;
        this.calculoPontoList = calculoPontoList;
    }

    public void analisar(Proposta proposta) {
        try {
            int pontos = calculoPontoList.stream().mapToInt(impl -> impl.calcular(proposta)).sum();
            proposta.setAprovada(pontos > 350);
        } catch (StrategyException ex) {
            proposta.setAprovada(false);
            proposta.setObservacao(ex.getMessage());
        }
        notificationRabbitMQService.notify(exchange, proposta);
    }
}
