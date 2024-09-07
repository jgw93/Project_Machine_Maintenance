package com.example.project_machine_maintenance.security.controller;

import com.example.project_machine_maintenance.domain.MemberRole;
import com.example.project_machine_maintenance.security.CustomUserDetailService;
import com.example.project_machine_maintenance.security.dto.MemberSecurityDTO;
import com.example.project_machine_maintenance.security.util.JWTUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@Log4j2
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final CustomUserDetailService customUserDetailService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, Object> loginRequest, HttpServletResponse response) {
        String mno = (String) loginRequest.get("username");
        String password = (String) loginRequest.get("password");
        boolean rememberMe = (Boolean) loginRequest.get("rememberMe");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(mno, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(Map.of("mno", mno));

        // 사용자 정보를 가져와서 mno, m_name, roleSet을 응답에 포함
        MemberSecurityDTO userDetails = (MemberSecurityDTO) authentication.getPrincipal();
        String Name = userDetails.getName();
        Set<MemberRole> roleSet = userDetails.getRoleSet();


        return ResponseEntity.ok().body(Map.of("accessToken", jwt, "mno", mno, "name", Name, "roleSet", roleSet));
    }


    @PostMapping("/refresh")
    public ResponseEntity<?> refreshAccessToken(@RequestBody Map<String, String> tokenRequest) {
        String refreshToken = tokenRequest.get("refreshToken");

        try {
            Claims claims = jwtUtil.validateToken(refreshToken);
            String mno = (String) claims.get("mno");

            String newAccessToken = jwtUtil.generateToken(Map.of("mno", mno));

            return ResponseEntity.ok().body(Map.of("accessToken", newAccessToken));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid refresh token");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        SecurityContextHolder.clearContext(); // 인증 정보 제거

        return ResponseEntity.ok().body("Logged out successfully");
    }
}
