package com.gym.gymmanagement.service;

import com.gym.gymmanagement.entity.Member;
import com.gym.gymmanagement.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // GET ALL MEMBERS
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // SAVE MEMBER
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    // FIND MEMBER BY ID
    public Member getMemberById(Long id) {
        return memberRepository.findById(id).orElse(null);
    }

    // DELETE MEMBER
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    // PAGINATION + SEARCH
    public Page<Member> getMembersPaginated(
            int page,
            int size,
            String keyword
    ) {

        //Creates pages
        PageRequest pageable = PageRequest.of(page, size);

        if (keyword == null || keyword.isEmpty()) {
            return memberRepository.findAll(pageable);
        }

        return memberRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                        keyword,
                        keyword,
                        pageable
                );
    }

    // DASHBOARD KPI : TOTAL MEMBERS
    public Long countMembers() {
        return memberRepository.countAllMembers();
    }

    // DASHBOARD KPI : TOTAL REVENUE
    public Double calculateRevenue() {

        Double revenue = memberRepository.calculateTotalRevenue();

        return revenue != null ? revenue : 0.0;
    }

    // DASHBOARD KPI : MEMBERS BY PLAN
    public List<Object[]> countMembersByPlan() {
        return memberRepository.countMembersByPlan();
    }

    // MOST POPULAR PLAN
    public Object[] getMostPopularPlan() {

        List<Object[]> plans =
                memberRepository.getMostPopularPlans();

        if (plans.isEmpty()) {
            return null;
        }

        return plans.get(0);
    }

    // RECENT MEMBERS
    public List<Member> getRecentMembers() {
        return memberRepository.findTop5ByOrderByRegistrationDateDesc();
    }
}