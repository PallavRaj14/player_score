package in.cypher.playerscore;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Score Card", contact = @Contact(name = "Pallav Raj", url = "https://github.com/pallavraj14", email = "pallav.raj14@gmail.com"), version = "0.0.1-SNAPSHOT"))
public class PlayerScoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlayerScoreApplication.class, args);
    }

}
