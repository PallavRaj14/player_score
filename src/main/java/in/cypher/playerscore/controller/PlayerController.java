package in.cypher.playerscore.controller;

import in.cypher.playerscore.beans.request.PlayerGetAllRequest;
import in.cypher.playerscore.beans.request.PlayerRequest;
import in.cypher.playerscore.beans.request.PlayerScoreHistoryRequest;
import in.cypher.playerscore.beans.response.PlayerHistoryResponse;
import in.cypher.playerscore.beans.response.PlayerResponse;
import in.cypher.playerscore.beans.response.PlayerScoreResponse;
import in.cypher.playerscore.schema.ScoreCard;
import in.cypher.playerscore.service.PlayerScoreService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/player")
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class PlayerController {

    @Autowired
    PlayerResponse playerResponse;

    @Autowired
    PlayerScoreService playerScoreService;

    @Operation(summary = "This API takes player information as parameters and stores into Database, created_at date must be in this format 'dd-MM-yyyy HH:mm:ss'")
    @PostMapping(value = "/add/score", produces = "application/json")
    public PlayerResponse addPlayerScore(@Valid @RequestBody PlayerRequest playerRequest, BindingResult result) {
        String response = null;
        try {
            if (result.hasErrors()) {
                playerResponse.setMessage("Null value has been here");
                return playerResponse;
            }
            response = playerScoreService.addPlayerScore(playerRequest);

        } catch (Exception e) {

        }
        playerResponse.setMessage(response);
        return playerResponse;
    }

    @GetMapping(value = "/get-score")
    public PlayerScoreResponse playerById(@Valid @RequestParam(value = "player_id") int player_id) {
        return playerScoreService.getScoreByPlayerId(player_id);
    }

    @DeleteMapping(value = "/delete-score")
    public PlayerResponse removeScoreById(@Valid @RequestParam(value = "player_id") int player_id) {
        playerResponse.setMessage(playerScoreService.deletePlayerById(player_id));
        return playerResponse;
    }

    @Operation(summary = "This API may get scores 'before date', 'after date', " +
            "'score by list of player name/ by 1 player name', 'between dates', 'after date', " +
            "'before & after date format could be yyyy-MM-dd HH:mm:ss'")
    @GetMapping(value = "/get-filtered")
    public List<ScoreCard> getAllRecords(@Valid final PlayerGetAllRequest playerGetAllRequest, BindingResult result) {
        List<ScoreCard> scoreCardList = playerScoreService.getAllRecords(playerGetAllRequest);
        return scoreCardList;
    }

    /**
     * @param playerScoreHistoryRequest
     * @return
     * @example (topScore - ts, lowestScore - ls, avgScore - avg)
     */
    @GetMapping(value = "/history")
    public PlayerHistoryResponse getPlayerHistory(@Valid final PlayerScoreHistoryRequest playerScoreHistoryRequest) {
        PlayerHistoryResponse playerHistoryResponse = null;
        if (playerScoreHistoryRequest.getPlayerScoreType().equalsIgnoreCase("ts") || playerScoreHistoryRequest.getPlayerScoreType().equalsIgnoreCase("ls") || playerScoreHistoryRequest.getPlayerScoreType().equalsIgnoreCase("avg")) {
            playerHistoryResponse = playerScoreService.playerHistory(playerScoreHistoryRequest);
        }
        return playerHistoryResponse;
    }
}
