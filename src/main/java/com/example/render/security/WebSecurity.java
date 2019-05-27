package com.example.render.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

import com.example.render.exceptionhandler.CustomAccessDeniedHandler;

import javax.validation.constraints.NotNull;

@Configuration
@EnableWebSecurity
public class WebSecurity  extends WebSecurityConfigurerAdapter{

    private  BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserDetailsService userDetailsService;

    public WebSecurity(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    private   CustomAccessDeniedHandler accessDeniedHandler;

  
 
    
    protected void configure(HttpSecurity http) throws Exception{


        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/manage/gateway","/", "/register", "/login", "/verification","/user","/verification/{text}","/update_user","/forgetPass", "/forgetPass1/{text}","/forgetAccount","/setPass","/PassReset","/tag/comparision","/tag/statics","/error_page1","/user/suggestion","/sumUser/Add", "/falUser/Remove").permitAll()
                .antMatchers("/js/**", "/css/**", "/img/**").permitAll()
             
                .antMatchers("/user/**").hasAnyRole("USER")
                
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                
               ;
                


    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }


}
