package com.gym.gymmanagement.repository;

import com.gym.gymmanagement.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainerRepository
        extends JpaRepository<Trainer, Long> {
}