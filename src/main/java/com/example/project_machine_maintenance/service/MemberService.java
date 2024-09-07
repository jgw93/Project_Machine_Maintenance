package com.example.project_machine_maintenance.service;

import com.example.project_machine_maintenance.domain.Member;
import com.example.project_machine_maintenance.dto.MemberDTO;

public interface MemberService {
    void register(MemberDTO memberDTO);
}
