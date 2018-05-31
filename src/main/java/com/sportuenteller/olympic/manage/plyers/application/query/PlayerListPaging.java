package com.sportuenteller.olympic.manage.plyers.application.query;

import com.sportuenteller.olympic.common.code.GameType;
import com.sportuenteller.olympic.common.code.Item;
import com.sportuenteller.olympic.common.query.ListPaging;
import com.sportuenteller.olympic.manage.countries.application.query.CountryView;
import com.sportuenteller.olympic.manage.plyers.domain.Player;
import com.sportuenteller.olympic.manage.plyers.domain.PlayerSearchRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PlayerListPaging extends ListPaging<Player, PlayerView, PlayerSearchRequest> {

    private List<Item> gameTypeItems = new ArrayList<>();
    private List<Item> countryItems = new ArrayList<>();

    public PlayerListPaging(int currentPage, int offset) {
        super(currentPage, offset);
    }

    protected PlayerListPaging(Page<Player> page, PlayerSearchRequest params, List<CountryView> countries) {
        super(page, params, 0);

        this.gameTypeItems = GameType.items();
        this.countryItems = countries.stream()
                .map(m -> new Item(m.getCountryCode(), m.getCountryNameEn()))
                .collect(Collectors.toList());
    }

    @Override
    protected PlayerSearchRequest initParams() {
        return new PlayerSearchRequest();
    }

    @Override
    protected int initPageCount() {
        return 5;
    }

    @Override
    protected List<PlayerView> convert(List<Player> contents) {
        return contents.stream().map(m -> new PlayerView(m)).collect(Collectors.toList());
    }
}
