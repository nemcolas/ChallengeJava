package com.example.ChallengeSprint1.repository;

import com.example.ChallengeSprint1.model.Tratamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TratamentoRepository extends JpaRepository<Tratamento, Long> {
}
