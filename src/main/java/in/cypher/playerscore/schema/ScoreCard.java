package in.cypher.playerscore.schema;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
@Component
public class ScoreCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;

    public String playerName;

    public int score;

    public Timestamp created_at;

    public boolean isDeleted;
}
