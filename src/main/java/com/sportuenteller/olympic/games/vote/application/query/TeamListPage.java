package com.sportuenteller.olympic.games.vote.application.query;

import com.sportuenteller.olympic.common.code.GameType;
import com.sportuenteller.olympic.common.code.Item;
import com.sportuenteller.olympic.common.code.MedalType;
import com.sportuenteller.olympic.common.query.ListPage;
import com.sportuenteller.olympic.games.game.application.query.GameView;
import com.sportuenteller.olympic.games.vote.application.client.PlayerSummaryListClient;
import com.sportuenteller.olympic.games.vote.domain.team.Team;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class TeamListPage extends ListPage<Team, TeamView, TeamListPageSearchRequest> {

    private List<Item> gameTypeItems;
    private List<Item> games;
    private List<Item> players;
    private List<Item> countries;

    private long removeId;
    private List<Long> idList = new ArrayList<>();
    private List<Long> goldList = new ArrayList<>();
    private List<Long> silverList = new ArrayList<>();
    private List<Long> bronzeList = new ArrayList<>();

    private String currentStatus;
    private boolean terminated;

    private long scrollPosition;

    public TeamListPage(String gameCode, long gameId){
        super();
        this.params.setGameId(gameId);
        this.params.setGameCode(gameCode);
    }

    @Override
    protected List<TeamView> convert(List<Team> contents) {
        return contents.stream().map(m -> new TeamView(m)).collect(Collectors.toList());
    }
    protected TeamListPage(List<Team> contents
            , TeamListPageSearchRequest params
            , List<GameView> games
            , List<PlayerSummaryListClient.PlayerItem> players) {
        super(contents, params);

        if(this.contents != null){
            for(TeamView teamView : this.contents){
                if(teamView.getMedalTypeName().equals(MedalType.gold.name())){
                    this.goldList.add(teamView.getTeamId());
                }else if(teamView.getMedalTypeName().equals(MedalType.silver.name())){
                    this.silverList.add(teamView.getTeamId());
                }else if(teamView.getMedalTypeName().equals(MedalType.bronze.name())){
                    this.bronzeList.add(teamView.getTeamId());
                }
            }
        }

        this.setGameTypeItems(GameType.items());
        this.setGames(games);
        this.setPlayers(players);
        this.setCountries(this.players);
    }

    private void setGameTypeItems(List<Item> gameTypeItems) {
        this.gameTypeItems = gameTypeItems;
    }
    private void setGames(List<GameView> games) {

        GameView game = games.stream().filter(f -> f.getGameId() == this.params.getGameId()).findFirst().orElse(null);
        if(game != null){
              this.currentStatus = game.getStatusTypeName();
              this.terminated = game.isTermination();
        }
        this.games = games.stream()
                .map(m -> new Item(m.getGameId(), m.getGameNameEn(), m.getStatusTypeSubject(), m.getGameNameKr(), m.isTermination()))
                .collect(Collectors.toList());
    }
    private void setPlayers(List<PlayerSummaryListClient.PlayerItem> players) {
        List<Long> checkIdList = this.contents.stream()
                .map(m -> m.getTeamPlayers())
                .flatMap(i -> i.stream()).map(m1 -> m1.getPlayerId())
                .collect(Collectors.toList());

        this.players = players.stream()
                .filter(f -> !checkIdList.contains(f.getPlayerId()))
                .map(m -> new Item(m.getPlayerId(), m.getName(), m.getCountryCode(), m.getCountryNameEn(), m.isSexuality()))
                .sorted((e1,e2) -> e1.getSubject().compareTo(e2.getSubject()))
                .collect(Collectors.toList());
    }
    private void setCountries(List<Item> players) {
        this.countries = players.stream().map(m -> new Item(m.getSubject(), m.getDesc()))
                .distinct()
                .collect(Collectors.toList());
    }
    @Override
    protected TeamListPageSearchRequest initParams() {
        return new TeamListPageSearchRequest(GameType.alpine.getCode(), true, true);
    }
}
