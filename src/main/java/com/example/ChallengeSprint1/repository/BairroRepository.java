package com.example.ChallengeSprint1.repository;

import com.example.ChallengeSprint1.model.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long> {
}
