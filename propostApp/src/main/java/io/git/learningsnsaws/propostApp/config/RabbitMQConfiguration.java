package io.git.learningsnsaws.propostApp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Value("${rabbitmq.propostapendente.exchange}")
    private String exchange;

    @Bean
    public Queue createQueuePropostaPendenteMsAnaliseCredito() {
        return QueueBuilder.durable("proposta-pendente.ms-analise-credito").build();
    }

    @Bean
    public Queue createQueuePropostaPendenteMsNotificacao() {
        return QueueBuilder.durable("proposta-pendente.ms-notificacao").build();
    }

    @Bean
    public Queue createQueuePropostaConcluidaMsProposta() {
        return QueueBuilder.durable("proposta-concluida.ms-proposta").build();
    }

    @Bean
    public Queue createQueuePropostaConcluidaMsNotificacao() {
        return QueueBuilder.durable("proposta-concluida.ms-notificacao").build();
    }

    @Bean
    public RabbitAdmin createRabbitMQAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initializerAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public FanoutExchange creteFanoutExchangePropostaPendente() {
        return ExchangeBuilder.fanoutExchange(exchange).build();
    }

    @Bean
    public Binding createBindingPropostaPendenteMSAnaliseCredito() {
        return BindingBuilder.bind(createQueuePropostaPendenteMsAnaliseCredito())
                .to(creteFanoutExchangePropostaPendente());
    }

    @Bean
    public Binding createBindingPropostaPendenteMSNotificacao() {
        return BindingBuilder.bind(createQueuePropostaPendenteMsNotificacao())
                .to(creteFanoutExchangePropostaPendente());
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
            rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter());

        return rabbitTemplate;
    }
}
