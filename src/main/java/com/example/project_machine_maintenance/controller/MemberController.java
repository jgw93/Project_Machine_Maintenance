package com.example.project_machine_maintenance.controller;


import com.example.project_machine_maintenance.dto.MemberDTO;
import com.example.project_machine_maintenance.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("memberDTO", new MemberDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerMember(MemberDTO memberDTO) {
        log.info("회원가입 요청 데이터: " + memberDTO);

        memberService.register(memberDTO);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/main")
    public String showMainPage() {
        return "main";
    }
}
