package com.sportuenteller.olympic.manage.plyers.application.helper;


import com.sportuenteller.olympic.manage.plyers.domain.Player;
import com.sportuenteller.olympic.manage.plyers.domain.PlayerRepository;
import com.sportuenteller.olympic.manage.plyers.domain.PlayerSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindPlayerHelper {

    public List<Player> findList(PlayerRepository repository){
        return findList(null, repository);
    }
    public List<Player> findList(PlayerSearchRequest request, PlayerRepository repository){
        return repository.findList(request);
    }
    public Page<Player> findPage(PlayerSearchRequest request, PageRequest pageRequest, PlayerRepository repository){
        return repository.findList(request, pageRequest);
    }
}
