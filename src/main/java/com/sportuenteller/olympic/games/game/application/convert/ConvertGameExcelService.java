package com.sportuenteller.olympic.games.game.application.convert;

import com.sportuenteller.olympic.common.query.Order;
import com.sportuenteller.olympic.common.query.SortBuilder;
import com.sportuenteller.olympic.games.game.application.helper.FindGameHelper;
import com.sportuenteller.olympic.games.game.domain.Game;
import com.sportuenteller.olympic.games.game.domain.GameRepository;
import com.sportuenteller.olympic.games.game.domain.GameSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.io.File;
import java.util.List;


@Service
public class ConvertGameExcelService {
    @Autowired
    GameRepository repository;
    @Autowired
    FindGameHelper helper;
    @Autowired
    WriteGameExcel writeGameExcel;
    @Autowired
    ReadGameExcel readGameExcel;

    @Transactional
    public void upload(File file){
        List<Game> games = readGameExcel.read(file, helper.findList(repository));

        for(Game game : games){
            this.create(game);
        }
    }
    public File download(GameSearchRequest request){
        return writeGameExcel.write(helper.findList(request, SortBuilder.build(new Order[]{new Order(false, "gameType")}), repository));
    }
    private void create(Game game) {
        repository.save(game);
    }
}
