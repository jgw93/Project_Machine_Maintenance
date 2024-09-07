package com.example.project_machine_maintenance.service;

import com.example.project_machine_maintenance.domain.Member;

public interface MemberService {
    Member registerMember(Member member);
    Member findByUsername(String username);
}
