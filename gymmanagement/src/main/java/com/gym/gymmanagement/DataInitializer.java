package com.gym.gymmanagement;

import com.gym.gymmanagement.entity.Member;
import com.gym.gymmanagement.entity.MembershipPlan;
import com.gym.gymmanagement.entity.Trainer;
import com.gym.gymmanagement.entity.WorkoutClass;
import com.gym.gymmanagement.repository.MemberRepository;
import com.gym.gymmanagement.repository.MembershipPlanRepository;
import com.gym.gymmanagement.repository.TrainerRepository;
import com.gym.gymmanagement.repository.WorkoutClassRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final MembershipPlanRepository planRepo;
    private final TrainerRepository trainerRepo;
    private final WorkoutClassRepository classRepo;
    private final MemberRepository memberRepo;

    public DataInitializer(
            MembershipPlanRepository planRepo,
            TrainerRepository trainerRepo,
            WorkoutClassRepository classRepo,
            MemberRepository memberRepo
    ) {
        this.planRepo = planRepo;
        this.trainerRepo = trainerRepo;
        this.classRepo = classRepo;
        this.memberRepo = memberRepo;
    }

    @Override
    public void run(String... args) {
        if (planRepo.count() > 0 || memberRepo.count() > 0) {
            return;
        }

        MembershipPlan basic = planRepo.save(new MembershipPlan(null, "Essential Access", 65.0, 1, null));
        MembershipPlan pro = planRepo.save(new MembershipPlan(null, "Athletic Pro", 165.0, 3, null));
        MembershipPlan elite = planRepo.save(new MembershipPlan(null, "VIP Coaching Annual", 540.0, 12, null));

        trainerRepo.saveAll(List.of(
            new Trainer(null, "Karim Ben Ali", "Strength & Conditioning", "+216 98 210 340", "karim@ironforge-gym.tn"),
            new Trainer(null, "Sonia Trabelsi", "HIIT & Metabolic Endurance", "+216 98 540 120", "sonia@ironforge-gym.tn"),
            new Trainer(null, "Youssef Gharbi", "Mobility & Olympic Lifting", "+216 98 770 890", "youssef@ironforge-gym.tn")
        ));

        WorkoutClass hiit = classRepo.save(new WorkoutClass(null, "HIIT Burn Circuit", "Mon / Wed / Fri - 18:30", 18, null));
        WorkoutClass strength = classRepo.save(new WorkoutClass(null, "Powerlifting Lab", "Tue / Thu - 19:00", 12, null));
        WorkoutClass mobility = classRepo.save(new WorkoutClass(null, "Core & Mobility", "Sat - 10:30", 20, null));

        memberRepo.saveAll(List.of(
            new Member(null, "Amine", "Mansouri", "amine.m@mail.tn", "+216 55 101 202", 26, "m1.jfif", LocalDate.now().minusDays(2), elite, List.of(hiit, strength)),
            new Member(null, "Sarra", "Bouzid", "sarra.b@mail.tn", "+216 55 303 404", 24, "w1.jfif", LocalDate.now().minusDays(5), pro, List.of(hiit, mobility)),
            new Member(null, "Mehdi", "Jaziri", "mehdi.j@mail.tn", "+216 55 505 606", 29, "m2.jfif", LocalDate.now().minusDays(8), elite, List.of(strength)),
            new Member(null, "Ines", "Khelifi", "ines.k@mail.tn", "+216 55 707 808", 27, "w2.jfif", LocalDate.now().minusDays(12), pro, List.of(mobility, hiit)),
            new Member(null, "Rami", "Hammami", "rami.h@mail.tn", "+216 55 909 101", 31, "m3.jfif", LocalDate.now().minusDays(16), pro, List.of(strength, hiit)),
            new Member(null, "Lina", "Chebbi", "lina.c@mail.tn", "+216 55 212 313", 23, "w3.jfif", LocalDate.now().minusDays(21), basic, List.of(mobility))
        ));
    }
}
