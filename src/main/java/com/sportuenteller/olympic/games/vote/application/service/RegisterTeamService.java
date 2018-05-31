package com.sportuenteller.olympic.games.vote.application.service;

import com.sportuenteller.olympic.common.code.Item;
import com.sportuenteller.olympic.common.code.MedalType;
import com.sportuenteller.olympic.common.model.Countries;
import com.sportuenteller.olympic.common.model.Players;
import com.sportuenteller.olympic.games.game.application.service.ChangeGameService;
import com.sportuenteller.olympic.games.game.domain.GameId;
import com.sportuenteller.olympic.games.vote.application.client.PlayerSummaryListClient;
import com.sportuenteller.olympic.games.vote.application.helper.FindTeamHelper;
import com.sportuenteller.olympic.games.vote.domain.team.Team;
import com.sportuenteller.olympic.games.vote.domain.team.TeamId;
import com.sportuenteller.olympic.games.vote.domain.team.TeamPlayer;
import com.sportuenteller.olympic.games.vote.domain.team.TeamRepository;
import com.sportuenteller.olympic.games.vote.domain.voter.Voter;
import com.sportuenteller.olympic.games.vote.domain.voter.VoterRepository;
import com.sportuenteller.olympic.games.vote.domain.voter.VoterSearchRequest;
import com.sportuenteller.olympic.manage.plyers.application.dao.PlayerSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegisterTeamService {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    PlayerSummaryListClient client;
    @Autowired
    FindTeamHelper helper;
    @Autowired
    VoterRepository voterRepository;
    @Autowired
    ChangeGameService changeGameService;

    @Transactional
    public void registerTeam(List<Long> playerIdList, long gameId){
        List<PlayerSummaryListClient.PlayerItem> players = client.requestPlayerItemLIst(playerIdList);
        this.register(players, gameId);
    }

    @Transactional
    public void registerTeam(long gameId){
        List<PlayerSummaryListClient.PlayerItem> players = client.requestPlayerItemLIst(gameId);
        if(players != null){
            List<Long> checkIdList = helper.findList(gameId, teamRepository).stream()
                    .map(m -> m.getTeamPlayers())
                    .flatMap(i -> i.stream()).map(m1 -> m1.getPlayer().getId())
                    .collect(Collectors.toList());

            for(PlayerSummaryListClient.PlayerItem item
                    : players.stream().filter(f-> !checkIdList.contains(f.getPlayerId())).collect(Collectors.toList())){
                    this.register(Arrays.asList(new PlayerSummaryListClient.PlayerItem [] {item}), gameId);
            }
        }
    }

    @Transactional
    public void changeStatus(long gameId){
        changeGameService.changeStatus(gameId);
    }

    @Transactional
    public void registerGameResult(long gameId, List<Long> goldList, List<Long> silverList, List<Long> bronzeList){
        changeGameService.terminateGame(gameId);
        List<Voter> voters = voterRepository.findList(new VoterSearchRequest(gameId));

        List<Team> teams = helper.findList(gameId, teamRepository);

        //Cancel game result
        for(Voter voter : voters){
            voter.cancelGameResult(teams.stream()
                    .filter(f -> f.getMedalType() != null && !f.getMedalType().equals(MedalType.none)).collect(Collectors.toList()));
        }
        //Change medal
        for(Team team : teams){
            if(goldList.contains(team.getId().getValue())){
                team.changeMedal(MedalType.gold);
            }else if(silverList.contains(team.getId().getValue())){
                team.changeMedal(MedalType.silver);
            }else if(bronzeList.contains(team.getId().getValue())){
                team.changeMedal(MedalType.bronze);
            }else{
                team.changeMedal(MedalType.none);
            }
        }
        //Register game result
        for(Voter voter : voters){
            voter.registerGameResult(teams.stream()
                    .filter(f -> f.getMedalType() != null && !f.getMedalType().equals(MedalType.none)).collect(Collectors.toList()));
        }
    }

    private void register(List<PlayerSummaryListClient.PlayerItem> players, long gameId){
        List<Item> countries = players.stream().map(m -> new Item(m.getCountryCode(), m.getCountryNameKr(), m.getCountryNameEn())).distinct().collect(Collectors.toList());

        if(countries.size() > 1){
            return;
        }
        Item countryItem = countries.get(0);

        //Team player add
        List<TeamPlayer> teamPlayers = new ArrayList<>();
        teamPlayers.addAll(players.stream().map(m -> new TeamPlayer(new Players(m.getPlayerId(), m.getName()))).collect(Collectors.toList()));

        //Team domain create
        Team team = new Team(new TeamId(this.teamRepository.max()), new GameId(gameId)
                , new Countries(countries.get(0).getCode(), countries.get(0).getValue()
                , countries.get(0).getSubject()), teamPlayers);

        this.teamRepository.save(team);
    }

    @Transactional
    public void remove(long id) {
        this.teamRepository.remove(this.teamRepository.findById(new TeamId(id)));
    }
}
