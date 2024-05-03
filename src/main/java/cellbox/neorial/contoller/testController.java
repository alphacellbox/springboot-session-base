package cellbox.neorial.contoller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/")
public class testController {

    @GetMapping("test")
    public String test(@RequestParam("booobs") String hi){
        return "test"+ hi;
    }

    @GetMapping("boobs")
    public String boobs(){
        return "boobs";
    }
}
