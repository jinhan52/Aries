package com.kim.security.aries.exceptionHandel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kim.security.aries.common.DataResult;
import com.kim.security.aries.common.ResultStatus;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
public class AcsDenidedHandel implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest req , HttpServletResponse rsp, AccessDeniedException e) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();
        rsp.setStatus(HttpStatus.ACCEPTED.value());
        rsp.setContentType("application/json; charset=UTF-8");
        rsp.getWriter().println(objectMapper.writeValueAsString(DataResult.failure("您的权限不够,请联系管理员", ResultStatus.UNAUTHORIZED)));
        log.error("您的权限不够,请联系管理员");
    }
}
