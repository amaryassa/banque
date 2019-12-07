package com.banque.banque.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter  {
    @Autowired
    private DataSource dataSource;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.inMemoryAuthentication().withUser("admin").password("{noop}1234").roles("ADMIN","USER");
        // auth.inMemoryAuthentication().withUser("user").password("{noop}12345").roles("USER");
        auth.jdbcAuthentication()
            .dataSource(dataSource)
            .usersByUsernameQuery("SELECT username as principal, password as credentials, active  FROM users where username=?")
            . authoritiesByUsernameQuery("SELECT username as principal, role as role FROM users_roles where username=?")
            .rolePrefix("ROLE_")
            .passwordEncoder(new BCryptPasswordEncoder());

            

    }   
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.formLogin();
        http.formLogin().loginPage("/login");
        // http.authorizeRequests().antMatchers("/compte", "/consulterCompte").hasRole("USER");
        // http.authorizeRequests().antMatchers("/compte", "/consulterCompte").hasRole("ADMIN");
        // http.authorizeRequests().antMatchers("/saveOperation").hasRole("ADMIN");
        http.exceptionHandling().accessDeniedPage("/403");
    }

}