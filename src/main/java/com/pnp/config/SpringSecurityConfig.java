package com.pnp.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
//@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    
    @Autowired
    private DataSource datasource;
    
    private final String SQL_LOGIN = "select username, password , 1 from user where username=?";
    private final String ROLE_FETCH = "select u.username, r.name from user u join user_roles ur on u.user_id = ur.user_id join roles r on ur.role_id = r.role_id where username=?";
    
    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
	                .authorizeRequests()
	                .antMatchers("/webjars/**").permitAll()
	                .antMatchers("/", "/home", "/about").permitAll()
	                .antMatchers("admin/**").hasAnyRole("SUPER_ADMIN", "TEAM_ADMIN")
	                .antMatchers("user/**").hasAnyRole("USER")
	                .anyRequest().authenticated()
                .and()
	                .formLogin()
	                .loginPage("/login")
	                //.loginProcessingUrl("/login")
	                .successForwardUrl("/login-success")
	                .failureUrl("/login?error=true")
	                .permitAll()
                .and()
	                .logout()
	                .permitAll()
                .and()
                	.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

    	auth.jdbcAuthentication()
	     .dataSource(datasource)
	     .passwordEncoder(getEncoder())
	     .usersByUsernameQuery(SQL_LOGIN)
	     .authoritiesByUsernameQuery(ROLE_FETCH);
    	

        /*auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("password").roles("ADMIN");*/
    }

    
    //Spring Boot configured this already.
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
           .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
    
    @Bean
	public BCryptPasswordEncoder getEncoder(){
		return new BCryptPasswordEncoder();
	}
}
