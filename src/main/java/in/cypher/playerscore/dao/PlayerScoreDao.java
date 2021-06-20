package in.cypher.playerscore.dao;

import in.cypher.playerscore.schema.ScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @notes Turn off ONLY_FULL_GROUP_BY- SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));
 */
@Transactional
@Component
public interface PlayerScoreDao extends JpaRepository<ScoreCard, Integer> {

    // Getting score by player id
    @Query(value = "SELECT *  FROM score_card WHERE id = ?1 AND is_deleted = 0", nativeQuery = true)
    ScoreCard getById(int id);

    // logically delete ie. making isDeleted to true
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "UPDATE score_card SET is_deleted= true WHERE id = ?1", nativeQuery = true)
    void logicalDeleteById(int id);

    // Get score by player name and before date
    @Query(value = "SELECT * FROM score_card WHERE created_at <= ?1 AND player_name IN (?2) AND is_deleted = 0", nativeQuery = true)
    List<ScoreCard> getScoreByBeforeDateAndPlayerName(String beforeDate, List<String> playerNamesList);

    // Get score by player name and after date
    @Query(value = "SELECT * FROM score_card WHERE created_at >= ?1 AND player_name IN (?2) AND is_deleted = 0", nativeQuery = true)
    List<ScoreCard> getScoreByAfterDateAndPlayerName(String afterDate, List<String> playerNamesList);

    // Get score by player name
    @Query(value = "SELECT * FROM score_card WHERE player_name IN (?1)  AND is_deleted = 0", nativeQuery = true)
    List<ScoreCard> getPlayersScoreList(List<String> playerNamesList);

    // Get score between dates
    @Query(value = "SELECT player_name, score, created_at FROM score_card WHERE created_at BETWEEN ?1 AND ?2 AND is_deleted = 0", nativeQuery = true)
    List<ScoreCard> getBetweenTheDates(String afterDate, String beforeDate);

    // Get score between dates and by player name
    @Query(value = "SELECT player_name, score, created_at FROM score_card WHERE created_at BETWEEN ?1 AND ?2 AND player_name IN (?3) AND is_deleted = 0", nativeQuery = true)
    List<ScoreCard> getBetweenTheDatesAndPlayerName(String afterDate, String beforeDate, List<String> playerNamesList);

    // Get score before date
    @Query(value = "SELECT * FROM score_card WHERE created_at <= ?1 AND is_deleted = 0", nativeQuery = true)
    List<ScoreCard> getScoreBeforeDate(String beforeDate);

    // Get score after date
    @Query(value = "SELECT * FROM score_card WHERE created_at >= ?1 AND is_deleted = 0", nativeQuery = true)
    List<ScoreCard> getScoreAfterDate(String afterDate);

    // Get player max score
    @Query(value = "SELECT Max(score) AS playerScore FROM score_card where player_name= ?1 AND is_deleted = 0", nativeQuery = true)
    int getTopScore(String playerName);

    // Get player min score
    @Query(value = "SELECT MIN(score) AS playerScore FROM score_card where player_name= ?1 AND is_deleted = 0", nativeQuery = true)
    int getLowScore(String playerName);

    // Get player average score
    @Query(value = "SELECT AVG(score) AS playerScore, created_at, id, player_name, score, is_deleted FROM score_card where player_name= ?1 AND is_deleted = 0", nativeQuery = true)
    int getAvgScore(String playerName);

    // Get player information using player score and name
    @Query(value = "SELECT * FROM score_card where score=?1 AND player_name=?2 AND is_deleted = 0", nativeQuery = true)
    ScoreCard getPlayerNameAndDateByScore(int score, String playerName);
}