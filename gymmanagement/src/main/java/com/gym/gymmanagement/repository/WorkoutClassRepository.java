package com.gym.gymmanagement.repository;

import com.gym.gymmanagement.entity.WorkoutClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkoutClassRepository
        extends JpaRepository<WorkoutClass, Long> {

    @Query("""
           SELECT c.name, COUNT(m)
           FROM Member m
           JOIN m.classes c
           GROUP BY c.name
           """)
    List<Object[]> countMembersPerClass();
}