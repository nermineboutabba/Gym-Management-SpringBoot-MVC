package com.gym.gymmanagement.controller;

import com.gym.gymmanagement.entity.WorkoutClass;
import com.gym.gymmanagement.service.WorkoutClassService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/classes")
public class WorkoutClassController {

    private final WorkoutClassService workoutClassService;

    public WorkoutClassController(
            WorkoutClassService workoutClassService
    ) {
        this.workoutClassService = workoutClassService;
    }

    @GetMapping
    public String listClasses(Model model) {

        model.addAttribute("classes",
                workoutClassService.getAllClasses());

        return "classes/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {

        model.addAttribute("workoutClass",
                new WorkoutClass());

        return "classes/form";
    }

    @PostMapping("/save")
    public String saveClass(
            @ModelAttribute WorkoutClass workoutClass
    ) {

        workoutClassService.saveClass(workoutClass);

        return "redirect:/classes";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model
    ) {

        model.addAttribute("workoutClass",
                workoutClassService.getClassById(id));

        return "classes/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteClass(@PathVariable Long id) {

        workoutClassService.deleteClass(id);

        return "redirect:/classes";
    }
}