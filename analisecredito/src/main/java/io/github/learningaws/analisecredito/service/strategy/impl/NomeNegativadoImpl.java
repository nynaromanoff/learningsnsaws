package io.github.learningaws.analisecredito.service.strategy.impl;

import io.github.learningaws.analisecredito.domain.Proposta;
import io.github.learningaws.analisecredito.service.strategy.CalculoPonto;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class NomeNegativadoImpl implements CalculoPonto {

    @Override
    public int calcular(Proposta proposta) {
        if(nomeNegativado()) {
            throw new RuntimeException("Nome Negativado");
        }
        return 100;
    }

    private boolean nomeNegativado() {
        return new Random().nextBoolean();
    }
}
