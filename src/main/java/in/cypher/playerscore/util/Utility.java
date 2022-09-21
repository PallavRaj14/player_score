package in.cypher.playerscore.util;

import in.cypher.playerscore.beans.response.PlayerHistoryResponse;
import in.cypher.playerscore.beans.response.PlayerScoreResponse;
import in.cypher.playerscore.schema.ScoreCard;
import lombok.var;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.String.valueOf;
import static java.time.LocalDateTime.from;

@Component
public class Utility {

    public PlayerScoreResponse playerScoreResponseMapper(ScoreCard scoreCard) {
        PlayerScoreResponse playerScoreResponse = new PlayerScoreResponse();
        playerScoreResponse.setId(scoreCard.getId());
        playerScoreResponse.setCreated_at((scoreCard.getCreated_at()).toString());
        playerScoreResponse.setPlayerName(scoreCard.getPlayerName());
        playerScoreResponse.setScore(scoreCard.getScore());
        return playerScoreResponse;
    }

    public LocalDateTime dateTimeConverter(String dateAndTime) {
        String pattern = "dd-MM-yyyy HH:mm:ss";
        var formatter = DateTimeFormatter.ofPattern(pattern);
        return from(formatter.parse(dateAndTime));
    }

    public PlayerHistoryResponse playerHistoryResponseMapper(int score, ScoreCard scoreCard) {
        PlayerHistoryResponse playerHistoryResponse = new PlayerHistoryResponse();
        playerHistoryResponse.setPlayerScore(score);
        playerHistoryResponse.setPlayerName(scoreCard.getPlayerName());
        playerHistoryResponse.setCreate_at(valueOf(scoreCard.getCreated_at()));
        return playerHistoryResponse;
    }
}
