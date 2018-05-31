package com.sportuenteller.olympic.games.game.infra.excel;

import com.sportuenteller.olympic.common.code.GameType;
import com.sportuenteller.olympic.common.convert.ExcelConvert;
import com.sportuenteller.olympic.common.excel.game.GameExcelRead;
import com.sportuenteller.olympic.common.excel.game.PoiGameExcelConvert;
import com.sportuenteller.olympic.common.excel.game.WriteGameExcelRequest;
import com.sportuenteller.olympic.common.model.Name;
import com.sportuenteller.olympic.games.game.application.convert.ReadGameExcel;
import com.sportuenteller.olympic.games.game.application.convert.WriteGameExcel;
import com.sportuenteller.olympic.games.game.domain.Game;
import com.sportuenteller.olympic.games.game.domain.GameId;
import com.sportuenteller.olympic.games.game.domain.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class GameExcelConvert extends ExcelConvert<Game, GameExcelRead, WriteGameExcelRequest>
    implements ReadGameExcel, WriteGameExcel{

    @Autowired
    PoiGameExcelConvert convert;

    @Override
    protected List<GameExcelRead> convert(List<GameExcelRead> excelDataList) {
        return excelDataList;
    }

    @Override
    protected List<GameExcelRead> readExcel(File excel) {
        return convert.read(excel);
    }

    @Override
    protected File writeExcel(List<WriteGameExcelRequest> requests) {
        return convert.write(requests);
    }

    @Override
    protected boolean checkModify(GameExcelRead excelData) {
        return excelData.isModify();
    }

    @Override
    protected Game findDomain(List<Game> games, GameExcelRead excelData) {
        return games.stream().filter(f -> f.getId().equals(new GameId(excelData.getId()))).findFirst().orElse(null);
    }

    @Override
    protected void modify(Game game, GameExcelRead excelData) {
        game.modifyGame(GameType.convert(excelData.getCode())
                , new Name(excelData.getNameKr(), excelData.getNameEn())
                , new Schedule(excelData.getGameStartDate(), excelData.getGameEndDate())
                , excelData.getVoteStartDate() != null && excelData.getVoteEndDate() != null ? new Schedule(excelData.getVoteStartDate(), excelData.getVoteEndDate()) : null
                , excelData.isGroup());
    }

    @Override
    protected Game createDomain(GameExcelRead excelData) {
        return new Game(new GameId(excelData.getId())
                , GameType.convert(excelData.getCode())
                , new Name(excelData.getNameKr(), excelData.getNameEn())
                , new Schedule(excelData.getGameStartDate(), excelData.getGameEndDate())
                , excelData.getVoteStartDate() != null && excelData.getVoteEndDate() != null ? new Schedule(excelData.getVoteStartDate(), excelData.getVoteEndDate()) : null
                , excelData.isGroup());
    }

    @Override
    protected List<WriteGameExcelRequest> convert(Game game) {
        List<WriteGameExcelRequest> list = new ArrayList<>();

        list.add(new WriteGameExcelRequest(game.getId().getValue()
                , game.getGameType().name()
                , game.getGameType().getCode()
                , game.getGameType().getSubject()
                , game.getName().getNameKr()
                , game.getName().getNameEn()
                , game.getSchedule().getStartDate()
                , game.getSchedule().getEndDate()
                , game.getVoteSchedule().getStartDate()
                , game.getVoteSchedule().getEndDate()
                , game.getStatusType().name()
                , game.getStatusType().getSubject()
                , game.getGroup()
                , game.getTermination()));

       return list;
    }
}
