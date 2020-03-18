package org.oopp.server;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableOAuth2Sso
@Order(1)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * This method configures which links require login,
     * and whether the user can access certain links or not.
     *
     * @param http - the user request.
     * @throws Exception throws Exception.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/newuser")
                .hasAnyRole("USER", "ADMIN")
                .antMatchers("/**", "/index.html")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .httpBasic();

        http.csrf().disable();
    }
}
