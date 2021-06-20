package in.cypher.playerscore.service;

import in.cypher.playerscore.beans.request.PlayerGetAllRequest;
import in.cypher.playerscore.beans.request.PlayerRequest;
import in.cypher.playerscore.beans.request.PlayerScoreHistoryRequest;
import in.cypher.playerscore.beans.response.PlayerHistoryResponse;
import in.cypher.playerscore.beans.response.PlayerScoreResponse;
import in.cypher.playerscore.dao.PlayerScoreDao;
import in.cypher.playerscore.schema.ScoreCard;
import in.cypher.playerscore.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.sql.Timestamp.valueOf;

@Service
@RequiredArgsConstructor
public class PlayerScoreServiceImpl implements PlayerScoreService {

    @Autowired
    public PlayerScoreDao playerScoreDao;

    @Autowired
    public Utility utility;

    public PlayerScoreServiceImpl(PlayerScoreDao playerScoreDao, Utility utility) {
        this.playerScoreDao = playerScoreDao;
        this.utility = utility;
    }

    @Override
    public String addPlayerScore(PlayerRequest playerRequest) {
        try {
            ScoreCard scoreCard = new ScoreCard();
            scoreCard.setPlayerName(playerRequest.getPlayerName());
            scoreCard.setScore(parseInt(playerRequest.getScore()));
            scoreCard.setCreated_at(valueOf(utility.dateTimeConverter(playerRequest.getCreated_at())));

            playerScoreDao.save(scoreCard);
            return "score card created";
        } catch (Exception e) {
            e.printStackTrace();
            return "failed: score card did not created";
        }
    }

    @Override
    public PlayerScoreResponse getScoreByPlayerId(int id) {

        ScoreCard scoreCard = playerScoreDao.getById(id);
        if (null != scoreCard) {
            return utility.playerScoreResponseMapper(scoreCard);
        }
        return new PlayerScoreResponse();
    }

    @Override
    public String deletePlayerById(int id) {
        ScoreCard scoreCard = playerScoreDao.getById(id);
        if (null != scoreCard) {
            playerScoreDao.logicalDeleteById(id);
            return "player id " + id + " deleted";
        }
        return "player with id " + id + " does not exist";
    }

    @Override
    public List<ScoreCard> getAllRecords(PlayerGetAllRequest playerGetAllRequest) {
        List<ScoreCard> scoreCardList = new ArrayList<>();
        if (null != playerGetAllRequest.getPlayerNamesList() && !playerGetAllRequest.getPlayerNamesList().isEmpty()) {
            // Get player score list between dates and by player name
            if ((null != playerGetAllRequest.getBeforeDate() && !playerGetAllRequest.getBeforeDate().isEmpty())
                    && (null != playerGetAllRequest.getAfterDate() && !playerGetAllRequest.getAfterDate().isEmpty())) {
                scoreCardList = playerScoreDao.getBetweenTheDatesAndPlayerName(playerGetAllRequest.getAfterDate(), playerGetAllRequest.getBeforeDate(), playerGetAllRequest.getPlayerNamesList());
                return scoreCardList;
            } else if (null != playerGetAllRequest.getBeforeDate() && !playerGetAllRequest.getBeforeDate().isEmpty()) {
                // List of score with list of players from Before date
                scoreCardList = playerScoreDao.getScoreByBeforeDateAndPlayerName(playerGetAllRequest.getBeforeDate(), playerGetAllRequest.getPlayerNamesList());
                return scoreCardList;
            } else if (null != playerGetAllRequest.getAfterDate() && !playerGetAllRequest.getAfterDate().isEmpty()) {
                // List of score with list of players from After date
                scoreCardList = playerScoreDao.getScoreByAfterDateAndPlayerName(playerGetAllRequest.getBeforeDate(), playerGetAllRequest.getPlayerNamesList());
                return scoreCardList;
            }
            // List all score by player name, either 1 or as many
            scoreCardList = playerScoreDao.getPlayersScoreList(playerGetAllRequest.getPlayerNamesList());
            return scoreCardList;
        }

        if (null != playerGetAllRequest.getBeforeDate() && !playerGetAllRequest.getBeforeDate().isEmpty() && !playerGetAllRequest.getBeforeDate().equalsIgnoreCase("")) {
            if (null != playerGetAllRequest.getAfterDate() && !playerGetAllRequest.getAfterDate().isEmpty() && !playerGetAllRequest.getAfterDate().equalsIgnoreCase("")) {
                if (utility.dateTimeConverter(playerGetAllRequest.getAfterDate()).compareTo(utility.dateTimeConverter(playerGetAllRequest.getBeforeDate())) < 0) {
                    // Return player list between the dates
                    scoreCardList = playerScoreDao.getBetweenTheDates(playerGetAllRequest.getAfterDate(), playerGetAllRequest.getBeforeDate());
                }
                return scoreCardList;
            }
            // Return all player's list before date
            return playerScoreDao.getScoreBeforeDate(playerGetAllRequest.getBeforeDate());
        }

        if (null != playerGetAllRequest.getAfterDate() && !playerGetAllRequest.getAfterDate().isEmpty()) {
            // Return all player's list after the dates
            return playerScoreDao.getScoreAfterDate(playerGetAllRequest.getAfterDate());
        }
        // Return empty list, in case of empty request
        return scoreCardList;
    }

    /**
     * @param playerScoreHistoryRequest
     * @return
     */
    @Override
    public PlayerHistoryResponse playerHistory(PlayerScoreHistoryRequest playerScoreHistoryRequest) {
        PlayerHistoryResponse playerHistoryResponse = null;
        ScoreCard scoreCard = null;
        if (playerScoreHistoryRequest.getPlayerScoreType().equalsIgnoreCase("ts")) {
            int topScore = playerScoreDao.getTopScore(playerScoreHistoryRequest.getPlayerName());
            scoreCard = playerScoreDao.getPlayerNameAndDateByScore(topScore, playerScoreHistoryRequest.getPlayerName());
            playerHistoryResponse = utility.playerHistoryResponseMapper(topScore, scoreCard);
        } else if (playerScoreHistoryRequest.getPlayerScoreType().equalsIgnoreCase("ls")) {
            int minScore = playerScoreDao.getLowScore(playerScoreHistoryRequest.getPlayerName());
            scoreCard = playerScoreDao.getPlayerNameAndDateByScore(minScore, playerScoreHistoryRequest.getPlayerName());
            playerHistoryResponse = utility.playerHistoryResponseMapper(minScore, scoreCard);
        } else if (playerScoreHistoryRequest.getPlayerScoreType().equalsIgnoreCase("avg")) {
            int avgScore = playerScoreDao.getAvgScore(playerScoreHistoryRequest.getPlayerName());
            playerHistoryResponse = new PlayerHistoryResponse();
            playerHistoryResponse.setPlayerScore(avgScore);
            playerHistoryResponse.setPlayerName(playerScoreHistoryRequest.getPlayerName());
            long millis = System.currentTimeMillis();
            Date date = new java.sql.Date(millis);
            playerHistoryResponse.setCreate_at(date.toString());
        }
        return playerHistoryResponse;
    }
}
