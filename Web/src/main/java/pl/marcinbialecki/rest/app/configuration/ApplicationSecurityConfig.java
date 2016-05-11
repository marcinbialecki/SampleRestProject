package pl.marcinbialecki.rest.app.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import pl.marcinbialecki.rest.app.security.AjaxAuthenticationFailureHandler;
import pl.marcinbialecki.rest.app.security.AjaxAuthenticationSuccessHandler;
import pl.marcinbialecki.rest.app.security.AjaxLogoutSuccessHandler;
import pl.marcinbialecki.rest.app.security.Http401UnauthorizedEntryPoint;

/**
 * Created by Marcin Białecki on 2016-05-11.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@ComponentScan(basePackages = {"pl.marcinbialecki.rest.app.security"})
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;

    @Autowired
    private AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;

    @Autowired
    private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;

    @Autowired
    private Http401UnauthorizedEntryPoint authenticationEntryPoint;

    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
        .and()
                .authorizeRequests()
                .antMatchers("/index.html", "/home.html", "/login.html", "/").permitAll().anyRequest()
                .authenticated()
                .and()
                //.addFilterAfter(new CsrfCookieGeneratorFilter(), CsrfFilter.class)
                .exceptionHandling()
                //.accessDeniedHandler(new CustomAccessDeniedHandler())
                .authenticationEntryPoint(authenticationEntryPoint)
        .and()
            .formLogin()
            .loginProcessingUrl("/api/authentication")
            .successHandler(ajaxAuthenticationSuccessHandler)
            .failureHandler(ajaxAuthenticationFailureHandler)
            .usernameParameter("j_username")
            .passwordParameter("j_password")
            .permitAll()
        .and()
            .logout()
            .logoutUrl("/api/logout")
            .logoutSuccessHandler(ajaxLogoutSuccessHandler)
            .deleteCookies("JSESSIONID", "CSRF-TOKEN")
            .permitAll()
        .and()
            .headers()
            .frameOptions()
            .disable();
    }

}
