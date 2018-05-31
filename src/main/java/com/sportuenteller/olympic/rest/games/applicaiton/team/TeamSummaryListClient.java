package com.sportuenteller.olympic.rest.games.applicaiton.team;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public interface TeamSummaryListClient {
    @Data
    @NoArgsConstructor
    public class TeamIteam {

        private Long teamSeq;
        private String countryCode;
        private String countryNameKr;
        private String countryNameEn;
        private List<String> players = new ArrayList<>();
        private String medalCode;
        private Long vote;
        private boolean available = true;
        private boolean myVote;

        public TeamIteam(Long teamSeq
                , String countryCode
                , String countryNameKr
                , String countryNameEn
                , List<String> players
                , String medalCode
                , boolean available
                , List<String> voters, String sessionKey) {
            this.teamSeq = teamSeq;
            this.countryCode = countryCode;
            this.countryNameKr = countryNameKr;
            this.countryNameEn = countryNameEn;
            this.players = players;
            this.medalCode = medalCode;
            this.available = available;
            this.vote = voters != null ? voters.size() : 0L;

            if(voters != null){
               String key = voters.stream().filter(f -> f.equals(sessionKey)).findFirst().orElse(null);
               this.myVote = key != null ? true : false;
            }
        }
    }
    public List<TeamSummaryListClient.TeamIteam> requestTeamSummaryList(long gameId, String sessionKey);


}
