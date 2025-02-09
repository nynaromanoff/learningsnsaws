package io.github.learningaws.analisecredito.service.strategy.impl;

import io.github.learningaws.analisecredito.domain.Proposta;
import io.github.learningaws.analisecredito.service.strategy.CalculoPonto;
import org.springframework.stereotype.Component;

@Component
public class PrazoPagamentoInferiorDezAnos implements CalculoPonto {
    @Override
    public int calcular(Proposta proposta) {
        return proposta.getPrazoPagamento() <= 120 ? 80 :0;
    }
}
