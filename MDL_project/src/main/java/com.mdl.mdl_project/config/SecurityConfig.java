package com.mdl.mdl_project.config;

import com.mdl.mdl_project.connection.security.jwt.JwtConfigurer;
import com.mdl.mdl_project.connection.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http.cors().and()
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("**").permitAll()
                //Authentication
                .antMatchers("/auth/signin").permitAll()

                .antMatchers("/Users/**").permitAll()

                .antMatchers("/UsersName/**").permitAll()
                //SOA
                .antMatchers(HttpMethod.POST,"/Users/**/StateOfArts").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/Users/**/StateOfArts").permitAll()
                .antMatchers(HttpMethod.GET,"/StateOfArts/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/StateOfArts/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/StateOfArts/**").hasRole("USER")
                //Article
                .antMatchers(HttpMethod.GET,"/StateOfArts/**/articles/**").permitAll()
                .antMatchers(HttpMethod.POST,"/StateOfArts/**/articles").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/StateOfArts/**/articles/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/StateOfArts/**/articles/**").hasRole("ADMIN")
                //Derive
                .antMatchers("/Derive").permitAll()
                //Research
                .antMatchers("/research/**").permitAll()
                .antMatchers("/saveResearch/keyword/**/user/**").permitAll()

                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
        //@formatter:on
    }


}
