package in.cypher.playerscore.request;

import in.cypher.playerscore.beans.request.PlayerScoreHistoryRequest;

public class ScoreHistoryRequest {

    public static PlayerScoreHistoryRequest playerTopScoreHistoryRequest() {
        PlayerScoreHistoryRequest playerScoreHistoryRequest = new PlayerScoreHistoryRequest();
        playerScoreHistoryRequest.setPlayerName("Sachin Tendulkar");
        playerScoreHistoryRequest.setPlayerScoreType("ts");
        return playerScoreHistoryRequest;
    }

    public static PlayerScoreHistoryRequest playerLowScoreHistoryRequest() {
        PlayerScoreHistoryRequest playerScoreHistoryRequest = new PlayerScoreHistoryRequest();
        playerScoreHistoryRequest.setPlayerName("Sachin Tendulkar");
        playerScoreHistoryRequest.setPlayerScoreType("ls");
        return playerScoreHistoryRequest;
    }
}
