package com.sportuenteller.olympic.games.vote.application.query;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Data
@NoArgsConstructor
public class TeamListPageSearchRequest {

    private String gameCode;
    private String countryCode;
    private boolean male;
    private boolean female;
    private long gameId;

    public TeamListPageSearchRequest(String gameCode, boolean male, boolean female) {
        this.gameCode = gameCode;
        this.male = male;
        this.female = female;
    }

    public boolean isHasCheckbox(){
        return StringUtils.hasText(this.countryCode);
    }
}
