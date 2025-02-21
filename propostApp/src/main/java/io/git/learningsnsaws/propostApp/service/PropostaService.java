package io.git.learningsnsaws.propostApp.service;

import io.git.learningsnsaws.propostApp.dto.PropostaRequestDto;
import io.git.learningsnsaws.propostApp.dto.PropostaResponseDto;
import io.git.learningsnsaws.propostApp.entity.Proposta;
import io.git.learningsnsaws.propostApp.mapper.PropostaMapper;
import io.git.learningsnsaws.propostApp.repository.PropostaRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {

    private final PropostaRepository repository;

    private final NotificationRabbitMQService notificationRabbitMQService;

    private final String exchange;

    public PropostaService(PropostaRepository repository,
                           NotificationRabbitMQService notificationRabbitMQService,
                           @Value("${rabbitmq.propostapendente.exchange}") String exchange) {
        this.repository = repository;
        this.notificationRabbitMQService = notificationRabbitMQService;
        this.exchange = exchange;
    }

    public PropostaResponseDto criar(PropostaRequestDto requestDto) {
        Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDto);
        repository.save(proposta);

        notifyRabittMQ(proposta);
        return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
    }

    public void notifyRabittMQ(Proposta proposta) {
        try {
            notificationRabbitMQService.notify(proposta, exchange);
        } catch (RuntimeException ex) {
            proposta.setIntegrada(false);
            repository.save(proposta);
        }


    }

    public List<PropostaResponseDto> obterPropostas() {
      return PropostaMapper.INSTANCE.convertListEntityToListDto(repository.findAll());
    }
}
