package es.aromano;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .authorizeRequests()
                .antMatchers("/registration").permitAll()
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/")
                .failureUrl("/login?error")
            .and()
            .logout()
            	.logoutSuccessUrl("/login")
            	.permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/webjars/**","/resources/**", "/static/**", "/css/**", "/img/**");
    }


}
