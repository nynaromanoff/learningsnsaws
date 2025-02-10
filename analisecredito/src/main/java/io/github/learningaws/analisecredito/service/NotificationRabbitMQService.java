package io.github.learningaws.analisecredito.service;

import io.github.learningaws.analisecredito.domain.Proposta;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificationRabbitMQService {

    private RabbitTemplate rabbitTemplate;

    public void notify(String exchange, Proposta proposta) {
        rabbitTemplate.convertAndSend(exchange, "", proposta);
    }
}
