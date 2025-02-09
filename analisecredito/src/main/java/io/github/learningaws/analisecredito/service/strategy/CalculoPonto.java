package io.github.learningaws.analisecredito.service.strategy;

import io.github.learningaws.analisecredito.domain.Proposta;

public interface CalculoPonto {
    int calcular(Proposta proposta);
}
