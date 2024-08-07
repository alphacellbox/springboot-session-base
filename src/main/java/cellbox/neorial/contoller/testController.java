package cellbox.neorial.contoller;


import cellbox.neorial.enumaration.Roles;
import cellbox.neorial.model.User;
import cellbox.neorial.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class testController {

    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
    AuthenticationManager authenticationManager;
    SecurityContextRepository securityContextRepository;
    SessionRegistry sessionRegistry;

    @GetMapping("/register")
    @Transactional
    public String register(HttpServletRequest request, HttpServletResponse response) {
        Optional<User> email = userRepository.findFirstByEmail("hi@gmail.com");
        if (email.isPresent()) {
            return "email exist";
        }
        User a = userRepository.save(User.builder()
                .email("hi@gmail.com")
                .password(passwordEncoder.encode("dfg"))
                .role(Roles.USER)
                .enable(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build());

        return "hi";
    }

    @GetMapping("/b")
    public String b(HttpServletRequest request, HttpServletResponse response) {

        return "bbbbbbbbb";
    }

    @GetMapping("/")
    public String f(HttpServletRequest request, HttpServletResponse response) {
        return request.getSession().getId();
    }

    @Autowired
    HttpSessionEventPublisher httpSessionEventPublisher;
    //    CompositeSessionAuthenticationStrategy strategy;
    @Autowired
    private SessionAuthenticationStrategy sessionStrategy;

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
                "hi@gmail.com", "dfg");
        Authentication authentication = this.authenticationManager.authenticate(token);
        SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authentication);
        this.securityContextHolderStrategy.setContext(context);
        this.securityContextRepository.saveContext(context, request, response);
//        httpSessionEventPublisher.sessionCreated(new HttpSessionEvent(request.getSession()));
        sessionStrategy.onAuthentication(authentication,request,response);
//        sessionRegistry.registerNewSession(request.getSession().getId(),authentication.getPrincipal());
//        this.sessionRegistry.getSessionInformation("612271cb-903f-4431-a248-1307654fb7d7");
        return "hi";
    }
}
