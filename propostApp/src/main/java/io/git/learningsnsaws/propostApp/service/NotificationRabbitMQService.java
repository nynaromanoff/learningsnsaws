package io.git.learningsnsaws.propostApp.service;

import io.git.learningsnsaws.propostApp.entity.Proposta;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class NotificationRabbitMQService {

    private RabbitTemplate rabbitTemplate;

    public void notify(Proposta proposta, String exchange) {
        rabbitTemplate.convertAndSend(exchange, "", proposta);
    }
}
