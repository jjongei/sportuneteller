package com.sportuenteller.olympic.games.vote.domain.voter;

import com.sportuenteller.olympic.common.utils.DateUtil;
import com.sportuenteller.olympic.games.game.domain.GameId;
import com.sportuenteller.olympic.games.vote.domain.team.Team;
import com.sportuenteller.olympic.games.vote.domain.team.TeamId;
import lombok.Getter;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Entity
@Access(AccessType.FIELD)
@Table(name="voter")
public class Voter {
    @EmbeddedId
    private VoterId id;

    @Column(name = "user_id", columnDefinition = "nvarchar(128)", nullable = false)
    private String userId;

    @Column(name = "name", columnDefinition = "nvarchar(128)", nullable = false)
    private String name;

    @Column(name = "gold_count", columnDefinition = "bigint", nullable = false)
    private Long goldCount;

    @Column(name = "silver_count", columnDefinition = "bigint", nullable = false)
    private Long slverCount;

    @Column(name = "bronze_count", columnDefinition = "bigint", nullable = false)
    private Long bronzeCount;

    @Column(name = "point", columnDefinition = "bigint", nullable = false)
    private Long point;

    @ElementCollection
    @CollectionTable(name = "vote_team", joinColumns = @JoinColumn(name = "session_key"))
    @OrderColumn(name = "line_idx")
    private List<VoteTeam> voteTeams;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", columnDefinition = "datetime", nullable = false)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_date", columnDefinition = "datetime", nullable = false)
    private Date lastModifiedDate;

    protected Voter(){}

    public Voter(VoterId id, String userId, String name, List<VoteTeam> voteTeams, Long point) {
        this.setId(id);
        this.setUserId(userId);
        this.setName(name);

        if(voteTeams != null){
            this.setVoteTeams(voteTeams);
        }
        this.setGoldCount(0L);
        this.setSlverCount(0L);
        this.setBronzeCount(0L);
        this.setPoint(point);

        this.createDate = DateUtil.getCurrentDate();
        this.lastModifiedDate = DateUtil.getCurrentDate();
    }
    public List<TeamId> vote(GameId gameId, TeamId teamId){
        if(this.voteTeams == null) this.voteTeams = new ArrayList<>();
        List<TeamId> result = checkGameAndTeamIdExist(gameId, teamId);
        if(result != null){
            return result;
        }
        result = checkGameIdExist(gameId);
        this.voteTeams.add(new VoteTeam(gameId, teamId, DateUtil.getCurrentDate()));
        this.setVoteTeams(this.voteTeams);

        return result;

    }

    public void cancelGameResult(List<Team> teams){
        this.calculatorPoint(teams, -1);
    }

    public void registerGameResult(List<Team> teams){
        this.calculatorPoint(teams, 1);
    }

    private void calculatorPoint(List<Team> teams, long addtional){
        if(teams != null){
            for(Team team : teams){
                if(isCheckByVoteTeam(team.getId())){
                    if(team.isGold()){
                        this.changeGoldCount(1*addtional);
                        this.changePoint(1000*addtional);
                    }else if(team.isSiver()){
                        this.changeSilverCount(1*addtional);
                        this.changePoint(700*addtional);
                    }else if(team.isBronze()){
                        this.changeBronzeCount(1*addtional);
                        this.changePoint(500*addtional);

                    }
                }
            }
        }
    }

    private void changePoint(long point){
        this.point += point;
    }
    public void changeGoldCount(long count){
        this.goldCount += count;
    }
    public void changeSilverCount(long count){
        this.slverCount += count;
    }
    public void changeBronzeCount(long count){
        this.bronzeCount += count;
    }

    private boolean isCheckByVoteTeam(TeamId teamId){
        if(voteTeams == null){
            return false;
        }
        return this.voteTeams.stream()
                .filter(f -> f.getTeamId().equals(teamId)).findFirst().orElse(null) != null;
    }

    private void verifyAtLeastOnOrMoreVoteTeams(List<VoteTeam> voteTeams){
        if(voteTeams == null || voteTeams.isEmpty())
            throw new IllegalArgumentException("Vote teams");
    }

    private List<TeamId> checkGameAndTeamIdExist(GameId gameId, TeamId teamId){
        List<VoteTeam> voteTeams = this.voteTeams.stream()
                .filter(f -> f.getGameId().equals(gameId) && f.getTeamId().equals(teamId))
                .collect(Collectors.toList());

        return this.checkVoteTeamExist(voteTeams);
    }
    private List<TeamId> checkGameIdExist(GameId gameId){
        List<VoteTeam> voteTeams = this.voteTeams.stream()
                .filter(f -> f.getGameId().equals(gameId))
                .collect(Collectors.toList());

        return this.checkVoteTeamExist(voteTeams);
    }
    private List<TeamId> checkVoteTeamExist(List<VoteTeam> voteTeams){
        if (voteTeams != null && !voteTeams.isEmpty()) {
            this.voteTeams.removeAll(voteTeams);
            return voteTeams.stream().map(m -> m.getTeamId()).collect(Collectors.toList());
        }
        return null;
    }

    private void setId(VoterId id) {
        if(id == null) throw new IllegalArgumentException("id");
        this.id = id;
    }

    private void setUserId(String userId) {
        if(userId == null) throw new IllegalArgumentException("userId");
        this.userId = userId;
    }

    private void setName(String name) {
        if(name == null) throw new IllegalArgumentException("name");
        this.name = name;
    }

    private void setGoldCount(Long goldCount) {
        this.goldCount = goldCount != null ? goldCount : 0L;
    }

    private void setSlverCount(Long slverCount) {
        this.slverCount = slverCount != null ? slverCount : 0L;
    }

    private void setBronzeCount(Long bronzeCount) {
        this.bronzeCount = bronzeCount != null ? bronzeCount : 0L;
    }

    private void setPoint(Long point) {
        this.point = point != null ? point : 0L;
    }

    private void setVoteTeams(List<VoteTeam> voteTeams) {
        this.verifyAtLeastOnOrMoreVoteTeams(voteTeams);
        this.voteTeams = voteTeams;
    }
}
