package com.bluenimbus.spring.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bluenimbus.spring.auth.security.AuthJwtTokenFilterConfigurer;
import com.bluenimbus.spring.auth.security.AuthJwtTokenProvider;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private AuthJwtTokenProvider jwtTokenProvider;

  
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // Disable CSRF (cross site request forgery)
    http.csrf().disable();

    // No session will be created or used by spring security
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Entry points
    http.authorizeRequests()//
        .antMatchers("/swagger").permitAll()
        .antMatchers("/api/signin").permitAll()
        .antMatchers("/api/signup").permitAll()
        .antMatchers("/").permitAll()
        .antMatchers("/*.png").permitAll()
        .antMatchers("/*.ico").permitAll()
        .antMatchers("/*.json").permitAll()
        .antMatchers("/error").permitAll()
        // Disallow everything else..
        .anyRequest().authenticated()
        .and()
        .csrf().disable();
    // If a user try to access a resource without having enough permissions
    http.exceptionHandling().accessDeniedPage("/login");
    
    // Apply JWT
    http.apply(new AuthJwtTokenFilterConfigurer(jwtTokenProvider));
    

  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    // Allow swagger to be accessed without authentication
    web.ignoring().antMatchers("/v2/api-docs")//
        .antMatchers("/swagger-resources/**")//
        .antMatchers("/swagger-ui.html")//
        .antMatchers("/configuration/**")//
        .antMatchers("/webjars/**")//
        .antMatchers("/public")
        .antMatchers("/csrf")
        
        // Un-secure H2 Database (for testing purposes, H2 console shouldn't be unprotected in production)
        .and()
        .ignoring()
        .antMatchers("/h2-console/**/**");
  }

  
  @Bean
  public PasswordEncoder passwordEncoders() { 
    return new BCryptPasswordEncoder(10);
  }
  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
  }
}

