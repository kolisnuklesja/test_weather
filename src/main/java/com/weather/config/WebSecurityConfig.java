package com.weather.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Tanya on 26.01.2017.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/login", "/login/weather").access("hasRole('USER')")
                .and()
                .formLogin()
                .loginPage("/")
                .defaultSuccessUrl("/login")
                .loginProcessingUrl("/j_spring_security_check")
                .failureUrl("/?error=1")
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                .and()
                .logout()
                .logoutUrl("/j_spring_security_logout")
                .logoutSuccessUrl("/")
                .and()
                .csrf()
                .and()
                .rememberMe()
                .rememberMeParameter("remember")
                .rememberMeCookieName("remember-me-cookieName")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error403");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

}