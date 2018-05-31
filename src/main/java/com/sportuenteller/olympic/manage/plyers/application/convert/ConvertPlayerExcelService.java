package com.sportuenteller.olympic.manage.plyers.application.convert;

import com.sportuenteller.olympic.manage.plyers.application.helper.FindPlayerHelper;
import com.sportuenteller.olympic.manage.plyers.domain.Player;
import com.sportuenteller.olympic.manage.plyers.domain.PlayerRepository;
import com.sportuenteller.olympic.manage.plyers.domain.PlayerSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;

@Service
public class ConvertPlayerExcelService {
    @Autowired
    PlayerRepository repository;
    @Autowired
    FindPlayerHelper helper;
    @Autowired
    WritePlayerExcel writePlayerExcel;
    @Autowired
    ReadPlayerExcel readPlayerExcel;

    @Transactional
    public void upload(File file){
        List<Player> players = readPlayerExcel.read(file, helper.findList(repository));

        for(Player player : players){
           this.create(player);
        }
    }
    public File download(PlayerSearchRequest request){
        return writePlayerExcel.write(helper.findList(request, repository));
    }
    private void create(Player player) {
        repository.save(player);
    }
}
