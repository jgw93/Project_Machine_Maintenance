package com.example.project_machine_maintenance.security.dto;

import com.example.project_machine_maintenance.domain.MemberRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@ToString
public class MemberSecurityDTO extends User {

    private String mno;
    private String name;
    private String password;
    private String phone;
    private String email;
    private String address1;
    private String address2;
    private Set<MemberRole> roleSet;

    public MemberSecurityDTO(String username,
                             String m_name,
                             String password,
                             String phone,
                             String email,
                             String address1,
                             String address2,
                             Set<MemberRole> roleSet,
                             Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

        this.mno = username;
        this.name = m_name;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.address1 = address1;
        this.address2 = address2;
        this.roleSet = roleSet;
    }
}
