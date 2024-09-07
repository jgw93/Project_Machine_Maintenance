package com.example.project_machine_maintenance.repository;

import com.example.project_machine_maintenance.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {

    @Query("SELECT m FROM Member m JOIN FETCH m.roleSet WHERE m.mno = :username")
    Optional<Member> findByIdWithRoles(@Param("username") String username);
}
