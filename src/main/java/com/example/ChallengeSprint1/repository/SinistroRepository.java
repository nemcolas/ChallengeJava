package com.example.ChallengeSprint1.repository;

import com.example.ChallengeSprint1.model.Sinistro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SinistroRepository extends JpaRepository<Sinistro, Long> {
}
