package com.example.TP2_G4.security;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    protected void Configure(AuthenticationManagerBuilder auth)throws Exception {
        PasswordEncoder passwordEncoder=passwordEncoder();
        auth.inMemoryAuthentication().withUser("administrateur").password(passwordEncoder.encode("emsi")).roles("ADMIN","USER");
        auth.inMemoryAuthentication().withUser("utilisateur").password(passwordEncoder.encode("emsi")).roles("USER");
    }
    protected void Configure(HttpSecurity http) throws Exception{
        http.formLogin();
       // http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/list").hasRole("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/403");//l'utilisateur n a pas les droits


    }
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
