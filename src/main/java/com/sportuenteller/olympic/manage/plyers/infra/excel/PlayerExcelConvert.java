package com.sportuenteller.olympic.manage.plyers.infra.excel;

import com.querydsl.core.group.GroupBy;
import com.sportuenteller.olympic.common.code.GameType;
import com.sportuenteller.olympic.common.convert.ExcelConvert;
import com.sportuenteller.olympic.common.excel.player.*;
import com.sportuenteller.olympic.manage.countries.domain.CountryId;
import com.sportuenteller.olympic.manage.plyers.application.convert.ReadPlayerExcel;
import com.sportuenteller.olympic.manage.plyers.application.convert.WritePlayerExcel;
import com.sportuenteller.olympic.manage.plyers.domain.ParticipationGame;
import com.sportuenteller.olympic.manage.plyers.domain.Player;
import com.sportuenteller.olympic.manage.plyers.domain.PlayerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class PlayerExcelConvert extends ExcelConvert<Player, PlayerExcelRead, WritePlayerExcelRequest> implements ReadPlayerExcel, WritePlayerExcel {

    @Autowired
    PoiPlayerExcelConvert convert;

    @Override
    protected List<PlayerExcelRead> convert(List<PlayerExcelRead> excelDataList) {
        List<PlayerExcelRead> result = new ArrayList<>();

        Map<PlayerInfo, List<PlayerExcelRead>> map = excelDataList.stream()
                .collect(Collectors.groupingBy(PlayerExcelRead::getPlayerInfo));
        Iterator<PlayerInfo> itr = map.keySet().iterator();

        while(itr.hasNext()){
            PlayerInfo key = itr.next();
            List<PlayerExcelRead> list = map.get(key);
            result.add(new PlayerExcelRead(key, list == null ? Collections.emptyList() : list.stream()
                    .map(m -> m.getGameInfo()).collect(Collectors.toList())));
        }
        return result;
    }

    @Override
    protected List<PlayerExcelRead> readExcel(File excel) {
        return convert.read(excel);
    }

    @Override
    protected File writeExcel(List<WritePlayerExcelRequest> requests) {
        return convert.write(requests);
    }

    @Override
    protected boolean checkModify(PlayerExcelRead excelData) {
        PlayerInfo info = excelData.getPlayerInfo();
        if(info == null)
            return false;
        return info.isModify();
    }

    @Override
    protected Player findDomain(List<Player> players, PlayerExcelRead excelData) {
        return players.stream().filter(f -> f.getId().equals(new PlayerId(excelData.getPlayerInfo().getId()))).findFirst().orElse(null);
    }

    @Override
    protected void modify(Player player, PlayerExcelRead excelData) {
        player.modifyPlayer(GameType.convert(excelData.getPlayerInfo().getCode())
            , new CountryId(excelData.getPlayerInfo().getCountryCode())
            , excelData.getPlayerInfo().getCountryReference()
            , excelData.getPlayerInfo().getName()
            , excelData.getPlayerInfo().isSexuality()
            , excelData.getGames() == null ? Collections.emptyList() : excelData.getGames().stream()
                            .map(m -> new ParticipationGame(m.getGameId(), m.getGameNameKr(), m.getGameNameEn())).collect(Collectors.toList()));
    }

    @Override
    protected Player createDomain(PlayerExcelRead excelData) {
        return new Player(new PlayerId(excelData.getPlayerInfo().getId())
                , GameType.convert(excelData.getPlayerInfo().getCode())
                , new CountryId(excelData.getPlayerInfo().getCountryCode())
                , excelData.getPlayerInfo().getCountryReference()
                , excelData.getPlayerInfo().getName()
                , excelData.getPlayerInfo().isSexuality()
                , excelData.getGames() == null ? Collections.emptyList() : excelData.getGames().stream()
                .map(m -> new ParticipationGame(m.getGameId(), m.getGameNameKr(), m.getGameNameEn())).collect(Collectors.toList()));
    }

    @Override
    protected List<WritePlayerExcelRequest> convert(Player player) {
        if(player == null){
            return Collections.emptyList();
        }
        List<WritePlayerExcelRequest> list = new ArrayList<>();

        List<ParticipationGame> games = player.getParticipationGames();
        if(games == null){
            list.add(new WritePlayerExcelRequest(player.getId().getValue()
                , player.getName()
                , player.getSexuality()
                , player.getCountryId().getValue()
                , player.getCountryReference(), player.getGameType().getCode()
                , 0L
                , ""
                , ""));
        }else{
            for(ParticipationGame game : games){
                list.add(new WritePlayerExcelRequest(player.getId().getValue()
                        , player.getName()
                        , player.getSexuality()
                        , player.getCountryId().getValue()
                        , player.getCountryReference(), player.getGameType().getCode()
                        , game.getGameId()
                        , game.getGameNameKr()
                        , game.getGameNameEn()));
            }
        }
        return list;
    }
}
