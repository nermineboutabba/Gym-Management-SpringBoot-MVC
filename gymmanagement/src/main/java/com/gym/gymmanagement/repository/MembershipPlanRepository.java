package com.gym.gymmanagement.repository;

import com.gym.gymmanagement.entity.MembershipPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipPlanRepository
        extends JpaRepository<MembershipPlan, Long> {
}