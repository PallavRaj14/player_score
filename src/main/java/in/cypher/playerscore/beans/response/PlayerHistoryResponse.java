package in.cypher.playerscore.beans.response;

import lombok.Getter;
import lombok.Setter;

public class PlayerHistoryResponse {

    @Getter
    @Setter
    public int playerScore;

    @Getter
    @Setter
    public String playerName;

    @Getter
    @Setter
    public String create_at;
}
