package com.gym.gymmanagement.repository;

import com.gym.gymmanagement.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository
        extends JpaRepository<Member, Long> {

    // SEARCH + PAGINATION
    Page<Member> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName,
            String lastName,
            Pageable pageable
    );

    // TOTAL MEMBERS
    @Query("SELECT COUNT(m) FROM Member m")
    Long countAllMembers();

    // TOTAL REVENUE
    @Query("""
           SELECT SUM(m.membershipPlan.price)
           FROM Member m
           """)
    Double calculateTotalRevenue();

    // MEMBERS PER PLAN
    @Query("""
           SELECT m.membershipPlan.name, COUNT(m)
           FROM Member m
           GROUP BY m.membershipPlan.name
           """)
    List<Object[]> countMembersByPlan();

    // MOST POPULAR PLAN
    @Query("""
       SELECT m.membershipPlan.name, COUNT(m)
       FROM Member m
       WHERE m.membershipPlan IS NOT NULL
       GROUP BY m.membershipPlan.name
       ORDER BY COUNT(m) DESC
       """)
    List<Object[]> getMostPopularPlans();


    // RECENT MEMBERS
    List<Member> findTop5ByOrderByRegistrationDateDesc();
}