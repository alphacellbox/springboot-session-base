package cellbox.neorial;

import cellbox.neorial.model.Student;
import cellbox.neorial.repository.StudentRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class NeorialApplication implements CommandLineRunner {

    public static void main(String[] args) {

//        System.setProperty("http.proxyHost", "127.0.0.1");
//        System.setProperty("http.proxyPort", "8080");
//        System.setProperty("https.proxyHost", "127.0.0.1");
//        System.setProperty("https.proxyPort", "8080");
        SpringApplication.run(NeorialApplication.class, args);
    }

    @Autowired
    StudentRepo studentRepo;



    @Override
    public void run(String... args) throws Exception {
        studentRepo.save(Student.builder()
                .fname("a")
                .lname("A")
                .gender(1)
                .mark(16)
                .build());
        studentRepo.save(Student.builder()
                .fname("b")
                .lname("B")
                .gender(2)
                .mark(17)
                .build());
        studentRepo.save(Student.builder()
                .fname("c")
                .lname("C")
                .gender(2)
                .mark(8)
                .build());
        studentRepo.save(Student.builder()
                .fname("d")
                .lname("D")
                .gender(1)
                .mark(10)
                .build());
        studentRepo.save(Student.builder()
                .fname("e")
                .lname("E")
                .gender(2)
                .mark(13)
                .build());
    }
//    @Scheduled(fixedDelay = 2000)
//    public void scheduleFixedDelayTask() throws IOException {
//        System.out.println(
//                "Fixed delay task - " + System.currentTimeMillis() / 1000);
//
//
//    largePayloadService.sendLargePayload();
//    }
}
