package cellbox.neorial.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    @Bean
    public SecurityFilterChain a(HttpSecurity http) throws Exception {
       return http
               .securityMatcher("/api/*")
               .authorizeHttpRequests(authZ ->{
                   authZ.anyRequest().permitAll();
               })
               .build();
    }

}
