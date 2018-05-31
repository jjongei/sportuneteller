package com.sportuenteller.olympic.games.vote.application.rank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyStatusGameService {
    @Autowired
    MyStatusGameDao myStatusGameDao;

    public List<MyStatusGame> findMyStatusGame(List<Long> idList){
        return myStatusGameDao.selectList(idList);
    }
}
