package com.example.project_machine_maintenance.dto;

import com.example.project_machine_maintenance.domain.MemberRole;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDTO {

    private String mno; // 회원 고유 번호
    private String name; // 이름
    private String password; // 비밀번호
    private String phone; // 전화번호
    private String email; // 이메일
    private String address1; // 기본 주소
    private String address2; // 상세 주소
    private Set<MemberRole> roleSet; // 회원 역할 (Set으로 저장)
}
