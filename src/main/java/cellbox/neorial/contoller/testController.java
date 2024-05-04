package cellbox.neorial.contoller;


import cellbox.neorial.enumaration.Roles;
import cellbox.neorial.model.User;
import cellbox.neorial.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class testController {

     UsersRepository usersRepository;
     PasswordEncoder passwordEncoder;

    @GetMapping("test")
    public String test(@RequestParam("booobs") String hi){
        return "test"+ hi;
    }

    @GetMapping("boobs")
    public String boobs(){
        return "boobs";
    }
    @GetMapping("/t")
    public String t(){
        usersRepository.save(User.builder()
                        .email("hi")
                        .password(passwordEncoder.encode("dfg"))
                        .role(Roles.USER)
                .build());
        return "boobs";
    }
}
