package com.nure.readland.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        System.out.println("authentication success");
        String targetUrl = determineTargetUrl(authentication);
        if(httpServletResponse.isCommitted()){
            System.out.println("Response has already been committed, unable to redirect");
            return;
        }

        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, targetUrl);

        HttpSession session = httpServletRequest.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    public String determineTargetUrl(Authentication authentication){
        Map<String, String> roleTargetUrlMap = new HashMap<>();
        roleTargetUrlMap.put("user", "/user");
        roleTargetUrlMap.put("admin", "/admin");
        roleTargetUrlMap.put("lib", "/lib");

        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for(final GrantedAuthority authority : authorities){
            String authorityName = authority.getAuthority();
            if(roleTargetUrlMap.containsKey(authorityName)){
                return roleTargetUrlMap.get(authorityName);
            }
        }

        throw new IllegalStateException();
    }
}
