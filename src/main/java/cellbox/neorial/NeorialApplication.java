package cellbox.neorial;

import cellbox.neorial.service.LargePayloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

@SpringBootApplication
@EnableAsync
public class NeorialApplication {

    public static void main(String[] args) {
//        System.setProperty("http.proxyHost", "127.0.0.1");
//        System.setProperty("http.proxyPort", "8080");
//        System.setProperty("https.proxyHost", "127.0.0.1");
//        System.setProperty("https.proxyPort", "8080");
        SpringApplication.run(NeorialApplication.class, args);
    }
    @Autowired
    LargePayloadService largePayloadService;

//    @Scheduled(fixedDelay = 2000)
//    public void scheduleFixedDelayTask() throws IOException {
//        System.out.println(
//                "Fixed delay task - " + System.currentTimeMillis() / 1000);
//
//
//    largePayloadService.sendLargePayload();
//    }
}
