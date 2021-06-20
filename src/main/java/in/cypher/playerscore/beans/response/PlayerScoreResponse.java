package in.cypher.playerscore.beans.response;

import lombok.Getter;
import lombok.Setter;

public class PlayerScoreResponse {
    @Getter
    @Setter
    public int id;

    @Getter
    @Setter
    public String playerName;

    @Getter
    @Setter
    public int score;

    @Getter
    @Setter
    public String created_at;
}
