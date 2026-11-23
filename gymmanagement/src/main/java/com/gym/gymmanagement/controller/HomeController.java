package com.gym.gymmanagement.controller;

import com.gym.gymmanagement.service.MemberService;
import com.gym.gymmanagement.service.WorkoutClassService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final MemberService memberService;
    private final WorkoutClassService workoutClassService;

    public HomeController(
            MemberService memberService,
            WorkoutClassService workoutClassService
    ) {
        this.memberService = memberService;
        this.workoutClassService = workoutClassService;
    }

    @GetMapping("/")
    public String dashboard(Model model) {

        model.addAttribute(
                "totalMembers",
                memberService.countMembers()
        );

        model.addAttribute(
                "totalRevenue",
                memberService.calculateRevenue()
        );

        model.addAttribute(
                "membersByPlan",
                memberService.countMembersByPlan()
        );

        model.addAttribute(
                "popularPlan",
                memberService.getMostPopularPlan()
        );

        model.addAttribute(
                "recentMembers",
                memberService.getRecentMembers()
        );

        model.addAttribute(
                "classStats",
                workoutClassService.countMembersPerClass()
        );

        return "dashboard";
    }
}