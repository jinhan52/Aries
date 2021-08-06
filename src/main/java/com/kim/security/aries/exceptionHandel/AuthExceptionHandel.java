package com.kim.security.aries.exceptionHandel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kim.security.aries.common.DataResult;
import com.kim.security.aries.common.ResultStatus;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Log4j2
public class AuthExceptionHandel implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse rsp, AuthenticationException e) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        rsp.setStatus(HttpStatus.ACCEPTED.value());
        rsp.setContentType("application/json; charset=UTF-8");
        rsp.getWriter().println(objectMapper.writeValueAsString(DataResult.failure("认证失败", ResultStatus.UNAUTH)));
        log.error("用户认证失败");
    }
}
