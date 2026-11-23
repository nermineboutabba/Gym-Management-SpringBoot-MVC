package com.gym.gymmanagement.service;

import com.gym.gymmanagement.entity.MembershipPlan;
import com.gym.gymmanagement.repository.MembershipPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipPlanService {

    private final MembershipPlanRepository membershipPlanRepository;

    public MembershipPlanService(MembershipPlanRepository membershipPlanRepository) {
        this.membershipPlanRepository = membershipPlanRepository;
    }

    // GET ALL
    public List<MembershipPlan> getAllPlans() {
        return membershipPlanRepository.findAll();
    }

    // SAVE
    public MembershipPlan savePlan(MembershipPlan plan) {
        return membershipPlanRepository.save(plan);
    }

    // FIND BY ID
    public MembershipPlan getPlanById(Long id) {
        return membershipPlanRepository.findById(id).orElse(null);
    }

    // DELETE
    public void deletePlan(Long id) {
        membershipPlanRepository.deleteById(id);
    }
}