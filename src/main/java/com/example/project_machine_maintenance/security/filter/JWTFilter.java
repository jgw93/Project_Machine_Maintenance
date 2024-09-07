package com.example.project_machine_maintenance.security.filter;

import com.example.project_machine_maintenance.security.CustomUserDetailService;
import com.example.project_machine_maintenance.security.exception.AccessTokenException;
import com.example.project_machine_maintenance.security.util.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final CustomUserDetailService customUserDetailService;
    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        // 회원가입 경로는 필터를 거치지 않도록 예외 처리
        if ("/register".equals(requestURI)) {
            filterChain.doFilter(request, response);
            return;
        }

        String headerStr = request.getHeader("Authorization");

        if (headerStr == null || !headerStr.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String tokenStr = headerStr.substring(7);

        try {
            Map<String, Object> claims = jwtUtil.validateToken(tokenStr);
            String mno = (String) claims.get("mno");
            log.info("----------------------- mno : " + mno);

            UserDetails userDetails = customUserDetailService.loadUserByUsername(mno);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (ExpiredJwtException e) {
            log.error("ExpiredJwtException");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token expired");
            return;
        } catch (SignatureException e) {
            log.error("SignatureException");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token signature");
            return;
        } catch (MalformedJwtException e) {
            log.error("MalformedJwtException");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Malformed token");
            return;
        } catch (AccessTokenException e) {
            log.error("AccessTokenException");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid access token");
            return;
        }

        filterChain.doFilter(request, response);
    }




}
