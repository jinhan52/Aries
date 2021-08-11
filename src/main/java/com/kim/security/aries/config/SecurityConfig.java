package com.kim.security.aries.config;


import com.kim.security.aries.exceptionHandel.AcsDenidedHandel;
import com.kim.security.aries.exceptionHandel.AuthExceptionHandel;
import com.kim.security.aries.securityFilter.CustomAuthFilter;
import com.kim.security.aries.securityFilter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    CustomAuthFilter customAuthFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //关闭csrf
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //取消session,改为session无状态
        .and()
        .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
        .and()
        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) //添加 jwt校验filter
        .addFilterBefore(customAuthFilter,FilterSecurityInterceptor.class)  //添加自定义鉴权filter
        .exceptionHandling()
                .authenticationEntryPoint(new AuthExceptionHandel())  //添加鉴权失败处理
                .accessDeniedHandler(new AcsDenidedHandel())  //添加验证失败处理
        ;
    }

}
