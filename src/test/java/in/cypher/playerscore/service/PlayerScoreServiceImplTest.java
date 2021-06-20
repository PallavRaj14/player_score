package in.cypher.playerscore.service;

import in.cypher.playerscore.beans.response.PlayerHistoryResponse;
import in.cypher.playerscore.dao.PlayerScoreDao;
import in.cypher.playerscore.util.Utility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static in.cypher.playerscore.request.ScoreHistoryRequest.playerLowScoreHistoryRequest;
import static in.cypher.playerscore.request.ScoreHistoryRequest.playerTopScoreHistoryRequest;
import static in.cypher.playerscore.response.ScoreCardResponse.playerHistory_LowScore;
import static in.cypher.playerscore.response.ScoreCardResponse.playerHistory_LowScoreResponse;
import static in.cypher.playerscore.response.ScoreCardResponse.playerHistory_TopScore;
import static in.cypher.playerscore.response.ScoreCardResponse.playerHistory_TopScoreResponse;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.openMocks;

class PlayerScoreServiceImplTest {
    private PlayerScoreService playerScoreService;

    @Mock
    private PlayerScoreDao playerScoreDao;

    @Mock
    private Utility utility;

    @BeforeEach
    public void init() {
        openMocks(this);
        playerScoreService = new PlayerScoreServiceImpl(playerScoreDao, utility);
    }

    @Test
    public void getPlayerHistory_TopScore() {
        given(playerScoreDao.getTopScore("Sachin Tendulkar")).willReturn(200);
        given(playerScoreDao.getPlayerNameAndDateByScore(200, "Sachin Tendulkar")).willReturn(playerHistory_TopScore());
        given(utility.playerHistoryResponseMapper(200, playerHistory_TopScore())).willReturn(playerHistory_TopScoreResponse());

        PlayerHistoryResponse actual = playerScoreService.playerHistory(playerTopScoreHistoryRequest());

        assertEquals(playerHistory_TopScoreResponse().getPlayerScore(), actual.getPlayerScore());
        assertEquals(playerHistory_TopScoreResponse().getPlayerName(), actual.getPlayerName());
        assertEquals(playerHistory_TopScoreResponse().getCreate_at(), actual.getCreate_at());
    }

    @Test
    public void getPlayerHistory_LowScore() {

        given(playerScoreDao.getLowScore("Sachin Tendulkar")).willReturn(10);
        given(playerScoreDao.getPlayerNameAndDateByScore(10, "Sachin Tendulkar")).willReturn(playerHistory_LowScore());
        given(utility.playerHistoryResponseMapper(10, playerHistory_LowScore())).willReturn(playerHistory_LowScoreResponse());

        PlayerHistoryResponse actual = playerScoreService.playerHistory(playerLowScoreHistoryRequest());

        assertEquals(playerHistory_LowScoreResponse().getPlayerName(), actual.getPlayerName());
        assertEquals(playerHistory_LowScoreResponse().getPlayerScore(), actual.getPlayerScore());
        assertEquals(playerHistory_LowScoreResponse().getCreate_at(), actual.getCreate_at());
    }
}