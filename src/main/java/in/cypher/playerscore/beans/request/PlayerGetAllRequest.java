package in.cypher.playerscore.beans.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class PlayerGetAllRequest {

    @Getter
    @Setter
    String beforeDate;

    @Getter
    @Setter
    String afterDate;

    @Getter
    @Setter
    List<String> playerNamesList;

}
