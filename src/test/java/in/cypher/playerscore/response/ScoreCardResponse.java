package in.cypher.playerscore.response;

import in.cypher.playerscore.beans.response.PlayerHistoryResponse;
import in.cypher.playerscore.schema.ScoreCard;

import static java.sql.Timestamp.valueOf;

public class ScoreCardResponse {

    public static ScoreCard playerHistory_TopScore() {
        ScoreCard scoreCard = new ScoreCard();
        scoreCard.setId(2);
        scoreCard.setPlayerName("Sachin Tendulkar");
        scoreCard.setScore(200);
        scoreCard.setDeleted(false);
        scoreCard.setCreated_at(valueOf("2021-02-01 12:17:15"));
        return scoreCard;
    }

    public static PlayerHistoryResponse playerHistory_TopScoreResponse() {
        PlayerHistoryResponse playerHistoryResponse = new PlayerHistoryResponse();
        playerHistoryResponse.setPlayerScore(200);
        playerHistoryResponse.setCreate_at("2021-02-01 12:17:15");
        playerHistoryResponse.setPlayerName("Sachin Tendulkar");
        return playerHistoryResponse;
    }

    public static ScoreCard playerHistory_LowScore() {
        ScoreCard scoreCard = new ScoreCard();
        scoreCard.setId(3);
        scoreCard.setPlayerName("Sachin Tendulkar");
        scoreCard.setScore(10);
        scoreCard.setDeleted(false);
        scoreCard.setCreated_at(valueOf("2021-03-01 12:17:15"));
        return scoreCard;
    }

    public static PlayerHistoryResponse playerHistory_LowScoreResponse() {
        PlayerHistoryResponse playerHistoryResponse = new PlayerHistoryResponse();
        playerHistoryResponse.setPlayerScore(10);
        playerHistoryResponse.setCreate_at("2021-03-01 12:17:15");
        playerHistoryResponse.setPlayerName("Sachin Tendulkar");
        return playerHistoryResponse;
    }
}
