package mk.ukim.finki.wp;

/**
 * Created by Mile on 26/07/2016.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:spring/business-config.xml")
public class NajdiCimer {

    public static void main(String[] args) {
        SpringApplication.run(NajdiCimer.class, args);
    }
}