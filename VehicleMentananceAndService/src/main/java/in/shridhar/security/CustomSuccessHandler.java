package in.shridhar.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
//@Component

public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
                                        throws IOException, ServletException {

        for (GrantedAuthority auth : authentication.getAuthorities()) {
            System.out.println("Logged in with role: " + auth.getAuthority()); // Debug log
            if (auth.getAuthority().equals("ROLE_ADMIN")) {
                response.sendRedirect("/Userdata/all"); // Admin landing
                return;
            } else if (auth.getAuthority().equals("ROLE_USER")) {
                response.sendRedirect("/Userdata/reg"); // User landing
                return;
            }
        }

        // fallback
        response.sendRedirect("/");
    }
}
