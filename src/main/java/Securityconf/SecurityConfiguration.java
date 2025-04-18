package Securityconf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
       
            .formLogin(form -> form
                .loginPage("/login") 
                .loginProcessingUrl("/login")  
                .defaultSuccessUrl("/compte", true) 
                
            );
        return http.build();
    }

   @Bean
    public UserDetailsService users() {
        UserDetails user = User.withDefaultPasswordEncoder() 
            .username("eya")
            .password("12345")
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}