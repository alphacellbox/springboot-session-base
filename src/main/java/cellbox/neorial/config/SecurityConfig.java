package cellbox.neorial.config;

import cellbox.neorial.service.UsersManaging;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import javax.sql.DataSource;


@EnableWebSecurity
@AllArgsConstructor
@Configuration
public class SecurityConfig {

    UsersManaging usersManaging;

    @Bean
    public AuthenticationProvider DaoAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usersManaging);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository());
    }
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    @Order(1)
    public SecurityFilterChain a(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/b")
                .authorizeHttpRequests(authZ -> authZ.anyRequest().authenticated())
                .sessionManagement(manager ->
                        manager.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                                .sessionConcurrency((concurrency) -> concurrency
//                                        .maximumSessions(1)
//                                        .maxSessionsPreventsLogin(true)))
                                .maximumSessions(2)
                                .maxSessionsPreventsLogin(true)
                .sessionRegistry(sessionRegistry()))

//                .securityContext((securityContext) -> securityContext
//                        .requireExplicitSave(true))
//TODO uncomment this if u dont want to create any session when request is not authenticated
//                .requestCache((cache) ->{
//                    RequestCache nullRequestCache = new NullRequestCache();
//                    cache.requestCache(nullRequestCache);})
                .authenticationProvider(DaoAuthenticationProvider())
                .formLogin(AbstractHttpConfigurer::disable)
                .build();
    }
    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
    @Bean
    @Order(2)
    public SecurityFilterChain b(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/login","/register")
                .authorizeHttpRequests(authZ -> authZ.anyRequest().permitAll())
//                                .maximumSessions(2)
//                                .maxSessionsPreventsLogin(true))
//                .securityContext((securityContext) -> securityContext
//                        .requireExplicitSave(true))
//TODO uncomment this if u dont want to create any session when request is not authenticated
//                .requestCache((cache) ->{
//                    RequestCache nullRequestCache = new NullRequestCache();
//                    cache.requestCache(nullRequestCache);})
                .authenticationProvider(DaoAuthenticationProvider())
                .formLogin(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

}
