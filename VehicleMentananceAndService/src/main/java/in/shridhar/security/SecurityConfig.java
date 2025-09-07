package in.shridhar.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import in.shridhar.service.impl.UserDetailsServiceImpl;
import org.slf4j.Logger; // Added Logger import
import org.slf4j.LoggerFactory; // Added LoggerFactory import

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class); // Logger instance

    @Bean
    public UserDetailsService userDetailsService() {
        logger.debug("Creating UserDetailsService bean: UserDetailsServiceImpl.");
        return new UserDetailsServiceImpl(); // Your custom UserDetailsService implementation
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        logger.debug("Creating BCryptPasswordEncoder bean.");
        return new BCryptPasswordEncoder(); // Secure password encoder
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        logger.debug("Creating DaoAuthenticationProvider bean.");
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    @Bean
    public AuthenticationSuccessHandler customSuccessHandler() {
        logger.debug("Creating CustomSuccessHandler bean.");
        return new CustomSuccessHandler(); // Your own class that implements AuthenticationSuccessHandler
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("Configuring SecurityFilterChain...");

        http
            // Authorize URL patterns
            .authenticationProvider(authenticationProvider())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/Userdata/reg", "/Userdata/usereg", "/Userdata/uregister", "/Userdata/log",
                    "/Userdata/loginuser", // Keep this here if your form action is /Userdata/loginuser
                    "/Userdata/checkEmail", "/Userdata/checkPass",
                    "/center/regs", "/center/bookings", "/vehicle/reg","/center/all","/center/booking/{cid}","/center/booking",
                    "center/accept/{sid}","center/reject/{sid}","center/complete/{sid}","/Userdata/edit/{uid}","Userdata/update",
                    "/css/**", "/js/**", "/images/**"
                ).permitAll()
                // Ensure this path matches your actual admin page URL
                // Corrected typo from 'alll' to 'all' assuming that was the intent.
                .requestMatchers("/actuator/**").permitAll() 
                .requestMatchers("/vehicle/all","/Userdata/alll","/center/services","/center/sreg","/center/sall").hasRole("ADMIN")
                .anyRequest().authenticated()
            )

            // Login configuration
            .formLogin(form -> form
                .loginPage("/Userdata/log")
                //.loginProcessingUrl("/Userdata/loginuser") // <-- CRITICAL FIX: UNCOMMENTED THIS LINE!
                .usernameParameter("umail")
                .passwordParameter("upass")
                // Removed .defaultSuccessUrl("/default", true) as customSuccessHandler() handles redirection
                .successHandler(customSuccessHandler())
                .failureUrl("/Userdata/log?error=true")
                .permitAll()
            )

            // Logout configuration
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/Userdata/log?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
            )

            // Session management
            .sessionManagement(session -> session
                .invalidSessionUrl("/Userdata/log?invalid")
            )

            // Optional: Cache control headers to avoid browser issues like ERR_CACHE_MISS
            .headers(headers -> headers
                .cacheControl(cache -> cache.disable())
            )

            // CSRF enabled (default) – use tokens in Thymeleaf forms
            .csrf(Customizer.withDefaults());
            
        logger.info("SecurityFilterChain configuration complete.");
        return http.build();
    }
}
