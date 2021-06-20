package in.cypher.playerscore.beans.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

public class PlayerRequest {

    @Getter
    @Setter
    public int id;

    @Getter
    @Setter
    @NotEmpty
    public String playerName;

    @Getter
    @Setter
    @NotEmpty
    public String score;

    @Getter
    @Setter
    @NotEmpty
    public String created_at;
}
