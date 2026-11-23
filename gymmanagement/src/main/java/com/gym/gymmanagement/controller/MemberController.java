package com.gym.gymmanagement.controller;

import com.gym.gymmanagement.entity.Member;
import com.gym.gymmanagement.entity.WorkoutClass;
import com.gym.gymmanagement.service.MemberService;
import com.gym.gymmanagement.service.MembershipPlanService;
import com.gym.gymmanagement.service.WorkoutClassService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.*;
import java.util.UUID;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final MembershipPlanService membershipPlanService;
    private final WorkoutClassService workoutClassService;

    public MemberController(
            MemberService memberService,
            MembershipPlanService membershipPlanService,
            WorkoutClassService workoutClassService
    ) {
        this.memberService = memberService;
        this.membershipPlanService = membershipPlanService;
        this.workoutClassService = workoutClassService;
    }

    // LIST + PAGINATION + SEARCH
    @GetMapping
    public String listMembers(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String keyword
    ) {

        Page<Member> memberPage =
                memberService.getMembersPaginated(page, 5, keyword);

        model.addAttribute("memberPage", memberPage);
        model.addAttribute("keyword", keyword);

        return "members/list";
    }

    // MEMBER DETAILS
    @GetMapping("/details/{id}")
    public String viewDetails(@PathVariable Long id, Model model) {

        Member member = memberService.getMemberById(id);

        model.addAttribute("member", member);

        return "members/details";
    }

    // SHOW ADD FORM
    @GetMapping("/add")
    public String showAddForm(Model model) {

        model.addAttribute("member", new Member());

        model.addAttribute("plans",
                membershipPlanService.getAllPlans());

        model.addAttribute("classes",
                workoutClassService.getAllClasses());

        return "members/form";
    }

    // SAVE MEMBER
    @PostMapping("/save")
    public String saveMember(
            @ModelAttribute Member member,
            @RequestParam("imageFile") MultipartFile imageFile
    ) {

        try {
            // IMAGE UPLOAD
            if (!imageFile.isEmpty()) {

                String fileName =
                        UUID.randomUUID()
                                + "_"
                                + imageFile.getOriginalFilename();

                Path uploadPath =
                        Paths.get("uploads");

                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Files.copy(
                        imageFile.getInputStream(),
                        uploadPath.resolve(fileName),
                        StandardCopyOption.REPLACE_EXISTING
                );

                member.setPhoto(fileName);
            }

            // KEEP OLD IMAGE WHEN EDITING
            if (imageFile.isEmpty() && member.getId() != null) {

                Member oldMember =
                        memberService.getMemberById(member.getId());

                member.setPhoto(oldMember.getPhoto());
            }

            // Fix plan (Spring sends only ID)
            if (member.getMembershipPlan() != null) {
                member.setMembershipPlan(
                        membershipPlanService.getPlanById(
                                member.getMembershipPlan().getId()
                        )
                );
            }

            // Fix classes (IDs only → convert to full objects)
            if (member.getClasses() != null) {
                List<Long> ids = member.getClasses()
                        .stream()
                        .map(WorkoutClass::getId)
                        .toList();

                member.setClasses(
                        workoutClassService.getAllClasses()
                                .stream()
                                .filter(c -> ids.contains(c.getId()))
                                .toList()
                );
            }

            // REGISTRATION DATE
            if (member.getRegistrationDate() == null) {
                member.setRegistrationDate(LocalDate.now());
            }

            memberService.saveMember(member);
        } catch (Exception e) {

            e.printStackTrace();
        }

        return "redirect:/members";
    }

    // SHOW EDIT FORM
    @GetMapping("/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model
    ) {

        model.addAttribute("member",
                memberService.getMemberById(id));

        model.addAttribute("plans",
                membershipPlanService.getAllPlans());

        model.addAttribute("classes",
                workoutClassService.getAllClasses());

        return "members/form";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable Long id) {

        memberService.deleteMember(id);

        return "redirect:/members";
    }
}