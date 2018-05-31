package com.sportuenteller.olympic.games.vote.application.dao;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class VoteTeamSummary {
    private long teamId;
    private Set<String> voters;
}
