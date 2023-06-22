package com.sharshag.ff4jresearch.config;

import java.lang.annotation.Retention;

import org.ff4j.FF4j;
import org.ff4j.web.FF4jDispatcherServlet;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AutoConfigureAfter(FF4jConfig.class)
public class FF4JConsoleConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().formLogin();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder encoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        UserDetails user = User.withUsername("admin").password(encoder.encode("admin")).roles("USER", "ADMIN").build();
        manager.createUser(user);
        return manager;
    }
    
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean
    public FF4jDispatcherServlet ff4jDispatcherServlet(FF4j ff4j) {
        FF4jDispatcherServlet ff4jDispatcherServlet = new FF4jDispatcherServlet();
        ff4jDispatcherServlet.setFf4j(ff4j);
        return ff4jDispatcherServlet;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean(FF4jDispatcherServlet dispatcherServlet) {
        return new ServletRegistrationBean(dispatcherServlet,  "/ff4j-web-console/*");
    }
}
