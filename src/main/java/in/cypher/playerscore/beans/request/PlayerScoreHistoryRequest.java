package in.cypher.playerscore.beans.request;

import lombok.Getter;
import lombok.Setter;

public class PlayerScoreHistoryRequest {
    @Getter
    @Setter
    String playerName;

    @Getter
    @Setter
    String playerScoreType;

}
