package com.gym.gymmanagement.controller;

import com.gym.gymmanagement.entity.Trainer;
import com.gym.gymmanagement.service.TrainerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    public TrainerController(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @GetMapping
    public String listTrainers(Model model) {

        model.addAttribute("trainers",
                trainerService.getAllTrainers());

        return "trainers/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {

        model.addAttribute("trainer", new Trainer());

        return "trainers/form";
    }

    @PostMapping("/save")
    public String saveTrainer(
            @ModelAttribute Trainer trainer
    ) {

        trainerService.saveTrainer(trainer);

        return "redirect:/trainers";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model
    ) {

        model.addAttribute("trainer",
                trainerService.getTrainerById(id));

        return "trainers/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteTrainer(@PathVariable Long id) {

        trainerService.deleteTrainer(id);

        return "redirect:/trainers";
    }
}