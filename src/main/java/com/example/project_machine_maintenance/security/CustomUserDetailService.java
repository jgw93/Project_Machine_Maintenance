package com.example.project_machine_maintenance.security;

import com.example.project_machine_maintenance.domain.Member;
import com.example.project_machine_maintenance.repository.MemberRepository;
import com.example.project_machine_maintenance.security.dto.MemberSecurityDTO;
import com.example.project_machine_maintenance.security.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final JWTUtil jWTUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Member> result = memberRepository.findByIdWithRoles(username);


        log.info("--------------------------------------------------------" + result);
        Member member = result.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Collection<? extends GrantedAuthority> authorities = member.getRoleSet().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.name()))
                .collect(Collectors.toList());

        log.info("=================================== " + authorities);

        MemberSecurityDTO dto = new MemberSecurityDTO(
                member.getMno(),
                member.getName(),
                member.getPassword(),
                member.getPhone(),
                member.getEmail(),
                member.getAddress1(),
                member.getAddress2(),
                member.getRoleSet(),
                //ROLE_ADMIN, ROLE_TEACHER
                authorities
        );
        log.info(dto);
        return dto;
    }


}
