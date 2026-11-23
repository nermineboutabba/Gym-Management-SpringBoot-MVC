package com.gym.gymmanagement.controller;

import com.gym.gymmanagement.entity.MembershipPlan;
import com.gym.gymmanagement.service.MembershipPlanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/plans")
public class MembershipPlanController {

    private final MembershipPlanService membershipPlanService;

    public MembershipPlanController(
            MembershipPlanService membershipPlanService
    ) {
        this.membershipPlanService = membershipPlanService;
    }

    @GetMapping
    public String listPlans(Model model) {

        model.addAttribute("plans",
                membershipPlanService.getAllPlans());

        return "plans/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {

        model.addAttribute("plan", new MembershipPlan());

        return "plans/form";
    }

    @PostMapping("/save")
    public String savePlan(
            @ModelAttribute MembershipPlan plan
    ) {

        membershipPlanService.savePlan(plan);

        return "redirect:/plans";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model
    ) {

        model.addAttribute("plan",
                membershipPlanService.getPlanById(id));

        return "plans/form";
    }

    @GetMapping("/delete/{id}")
    public String deletePlan(@PathVariable Long id) {

        membershipPlanService.deletePlan(id);

        return "redirect:/plans";
    }
}