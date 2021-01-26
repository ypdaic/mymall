package com.ypdai.mymall.activiti.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class UserDetailsServiceConfig {

    private static SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("test");

    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    public static class MyUserDetailsService implements UserDetailsService {

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            UserDetails userDetails = User.withUsername(username).password("123456").authorities(simpleGrantedAuthority).build();

            return userDetails;
        }
    }
}
