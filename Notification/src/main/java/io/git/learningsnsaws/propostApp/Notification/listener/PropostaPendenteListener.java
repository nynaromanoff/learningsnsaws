package io.git.learningsnsaws.propostApp.Notification.listener;

import io.git.learningsnsaws.propostApp.Notification.constant.ConstantMessages;
import io.git.learningsnsaws.propostApp.Notification.domain.Proposta;
import io.git.learningsnsaws.propostApp.Notification.service.NotificationSnsService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PropostaPendenteListener {

    private NotificationSnsService notificationSnsService;

    @RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
    public void propostaPendente(Proposta proposta) {
        String message = String.format(ConstantMessages.PROPOSTA_EM_ANALISE, proposta.getUsuario().getNome());
        notificationSnsService.notify(proposta.getUsuario().getTelefone(), message);
    }
}
