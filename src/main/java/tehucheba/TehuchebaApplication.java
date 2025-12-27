package tehucheba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TehuchebaApplication {
    public static void main(String[] args) {
        SpringApplication.run(TehuchebaApplication.class, args);
//        String pass = new BCryptPasswordEncoder().encode("xxZZ1234567890");
//        System.out.println(pass);
    }

}
