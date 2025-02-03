package io.git.learningsnsaws.propostApp.repository;

import io.git.learningsnsaws.propostApp.entity.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {
    List<Proposta> findAllByIntegradaIsFalse();
}
