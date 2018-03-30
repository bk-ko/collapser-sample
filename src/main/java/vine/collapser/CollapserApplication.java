package vine.collapser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

@EnableCircuitBreaker
@SpringBootApplication
public class CollapserApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollapserApplication.class, args);
    }

}
