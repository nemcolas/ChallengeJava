package com.example.ChallengeSprint1.repository;

import com.example.ChallengeSprint1.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistaRepository extends JpaRepository<Dentista, Long> {
}
