package com.kim.security.aries.securityFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import java.io.IOException;

@Component
public class CustomAuthFilter extends AbstractSecurityInterceptor implements Filter {

    @Autowired
    CustomRoleGetFilter customRoleGetFilter;

    @Autowired
    CustomUrlDecisionFilter customUrlDecisionFilter;



    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        // 调用父类的 beforeInvocation ==> accessDecisionManager.decide(..)
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            // 调用父类的 afterInvocation ==> afterInvocationManager.decide(..)
            super.afterInvocation(token, null);
        }
    }

    @PostConstruct
    public void initSetManager() {
        super.setAccessDecisionManager(customUrlDecisionFilter);
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return customRoleGetFilter;
    }
}
