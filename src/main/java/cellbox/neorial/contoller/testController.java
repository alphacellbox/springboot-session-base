package cellbox.neorial.contoller;


import cellbox.neorial.enumaration.Roles;
import cellbox.neorial.model.User;
import cellbox.neorial.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
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
    @GetMapping("/register")
    @Transactional
    public String register(HttpServletRequest request, HttpServletResponse response) {
        Optional<User> email=userRepository.findFirstByEmail("hi@gmail.com");
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
//        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
//                "hi@gmail.com", "dfg");
//        Authentication authentication = this.authenticationManager.authenticate(token);
//        SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
//        context.setAuthentication(authentication);
//        this.securityContextHolderStrategy.setContext(context);

//        this.securityContextRepository.saveContext(context,request,response);
        return "hi";
    }
    @GetMapping("/b")
    public String b(HttpServletRequest request, HttpServletResponse response) {

        return "bbbbbbbbb";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(
                "hi@gmail.com", "dfg");
        Authentication authentication = this.authenticationManager.authenticate(token);
        SecurityContext context = this.securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authentication);
        this.securityContextHolderStrategy.setContext(context);
        this.securityContextRepository.saveContext(context,request,response);

        return "hi";
    }

}
