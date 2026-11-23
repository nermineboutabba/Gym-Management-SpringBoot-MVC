package com.gym.gymmanagement.service;

import com.gym.gymmanagement.entity.WorkoutClass;
import com.gym.gymmanagement.repository.WorkoutClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutClassService {

    private final WorkoutClassRepository workoutClassRepository;

    public WorkoutClassService(WorkoutClassRepository workoutClassRepository) {
        this.workoutClassRepository = workoutClassRepository;
    }

    public List<WorkoutClass> getAllClasses() {
        return workoutClassRepository.findAll();
    }

    public WorkoutClass saveClass(WorkoutClass workoutClass) {
        return workoutClassRepository.save(workoutClass);
    }

    public WorkoutClass getClassById(Long id) {
        return workoutClassRepository.findById(id).orElse(null);
    }

    public void deleteClass(Long id) {
        workoutClassRepository.deleteById(id);
    }

    public List<Object[]> countMembersPerClass() {
        return workoutClassRepository.countMembersPerClass();
    }
}