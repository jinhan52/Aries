package com.kim.security.aries.securityFilter;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import com.kim.security.aries.tools.JwtTools;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse rsp, FilterChain filterChain) throws ServletException, IOException {
        String token = req.getHeader("token");

        if (StringUtils.isNotBlank(token) && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (JwtTools.verifyToken(token)) {
                Claims claims = JwtTools.verifyTokenWithClaims(token);

                List<String> auth = (List<String>)claims.get("auths");

                List<SimpleGrantedAuthority> auths = new ArrayList<>();
                auth.forEach(item ->{
                    auths.add(new SimpleGrantedAuthority(item));
                });
                UsernamePasswordAuthenticationToken username = new UsernamePasswordAuthenticationToken(claims.get("username").toString(), null, auths);

                //存入上下文,下文需要拿到认证信息去进行判断权限
                SecurityContextHolder.getContext().setAuthentication(username);
            }
        }
        filterChain.doFilter(req,rsp);
    }
}
