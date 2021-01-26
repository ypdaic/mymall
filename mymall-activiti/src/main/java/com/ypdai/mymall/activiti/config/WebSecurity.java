package com.ypdai.mymall.activiti.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic().and().authorizeRequests().anyRequest()
//                .fullyAuthenticated();
//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////        http.csrf().disable();
//        super.configure(http);
//    }

    /**
     * 关闭csrf,否则非"GET", "HEAD", "TRACE", "OPTIONS"请求都会被CsrfFilter拦截
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        super.configure(http);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("root").password(new BCryptPasswordEncoder().encode("enjoy")).roles("USER").
                and().withUser("admin").password(new BCryptPasswordEncoder().encode("test1234")).roles("USER", "ADMIN");
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//
//        http.httpBasic().and().authorizeRequests().anyRequest()
//                .fullyAuthenticated();
//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }


    @Override
    public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**");
    }
}
