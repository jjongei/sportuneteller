package com.sportuenteller.olympic.manage.plyers.application.query;


import com.sportuenteller.olympic.manage.countries.application.query.CountryView;
import com.sportuenteller.olympic.manage.countries.application.query.CountryViewListService;
import com.sportuenteller.olympic.manage.plyers.application.helper.FindPlayerHelper;
import com.sportuenteller.olympic.manage.plyers.domain.PlayerRepository;
import com.sportuenteller.olympic.manage.plyers.domain.PlayerSearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerListPagingService {

    @Autowired
    PlayerRepository repository;
    @Autowired
    FindPlayerHelper helper;
    @Autowired
    CountryViewListService countryViewListService;

    public PlayerListPaging findPlayerPage(PlayerSearchRequest request, PageRequest pageRequest) {

        List<CountryView> countries = countryViewListService.findCountryItems(helper.findList(repository).stream()
                .map(m -> m.getCountryId().getValue())
                .distinct()
                .collect(Collectors.toList()));

        return new PlayerListPaging(helper.findPage(request, pageRequest, repository)
                , request
                , countries);
    }
}
