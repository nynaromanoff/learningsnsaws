package io.github.learningaws.analisecredito.service;

import io.github.learningaws.analisecredito.domain.Proposta;
import io.github.learningaws.analisecredito.service.strategy.CalculoPonto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaliseCreditoService {
    private List<CalculoPonto> calculoPontoList;

    public AnaliseCreditoService(List<CalculoPonto> calculoPontoList) {
        this.calculoPontoList = calculoPontoList;
    }

    public void analisar(Proposta proposta) {
      int pontuacao = calculoPontoList.stream().mapToInt(impl -> impl.calcular(proposta)).sum();
    }
}
