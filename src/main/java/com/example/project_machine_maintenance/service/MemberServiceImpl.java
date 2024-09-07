package com.example.project_machine_maintenance.service;

import com.example.project_machine_maintenance.domain.Member;
import com.example.project_machine_maintenance.domain.MemberRole;
import com.example.project_machine_maintenance.dto.MemberDTO;
import com.example.project_machine_maintenance.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public void register(MemberDTO memberDTO) {
        log.info(modelMapper);
        Member member = modelMapper.map(memberDTO, Member.class);
        member.changePassword(passwordEncoder.encode(memberDTO.getPassword()));

        if (member.getRoleSet() == null) {
            member.setRoleSet(new HashSet<>());
        }

        member.getRoleSet().add(MemberRole.USER);

        log.info("-------------------------------------------------");
        log.info(member);

        memberRepository.save(member);
    }
}
