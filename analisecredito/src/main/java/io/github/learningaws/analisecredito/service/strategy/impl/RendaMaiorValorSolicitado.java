package io.github.learningaws.analisecredito.service.strategy.impl;

import io.github.learningaws.analisecredito.domain.Proposta;
import io.github.learningaws.analisecredito.service.strategy.CalculoPonto;
import org.springframework.stereotype.Component;

@Component
public class RendaMaiorValorSolicitado implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        return rendaMaiorValorSolicitado(proposta) ? 100 : 0;
    }

    private boolean rendaMaiorValorSolicitado(Proposta proposta) {

        return proposta.getUsuario().getRenda() > proposta.getValorSolicitado();
    }
}
