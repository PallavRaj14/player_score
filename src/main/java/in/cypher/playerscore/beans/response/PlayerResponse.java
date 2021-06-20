package in.cypher.playerscore.beans.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class PlayerResponse {

    @Getter
    @Setter
    public String message;
}
