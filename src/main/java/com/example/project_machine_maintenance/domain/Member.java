package com.example.project_machine_maintenance.domain;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"roleSet"})
@Builder
public class Member extends BaseEntity {

    @Id
    private String mno;
    private String name;
    private String password;
    private String phone;
    private String email;
    private String address1;
    private String address2;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    public void changePassword(String m_password) {
        this.password = m_password;
    }

}
