package in.cypher.playerscore.service;

import in.cypher.playerscore.beans.request.PlayerGetAllRequest;
import in.cypher.playerscore.beans.request.PlayerRequest;
import in.cypher.playerscore.beans.request.PlayerScoreHistoryRequest;
import in.cypher.playerscore.beans.response.PlayerHistoryResponse;
import in.cypher.playerscore.beans.response.PlayerScoreResponse;
import in.cypher.playerscore.schema.ScoreCard;

import java.util.List;

public interface PlayerScoreService {

    String addPlayerScore(PlayerRequest playerRequest);

    PlayerScoreResponse getScoreByPlayerId(int id);

    String deletePlayerById(int id);

    List<ScoreCard> getAllRecords(PlayerGetAllRequest playerGetAllRequest);

    PlayerHistoryResponse playerHistory(PlayerScoreHistoryRequest playerScoreHistoryRequest);
}
