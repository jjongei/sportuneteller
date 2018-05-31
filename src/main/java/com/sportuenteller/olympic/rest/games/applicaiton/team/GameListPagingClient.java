package com.sportuenteller.olympic.rest.games.applicaiton.team;

import com.sportuenteller.olympic.common.utils.DateUtil;
import com.sportuenteller.olympic.rest.games.applicaiton.view.GameViewListClient;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

public interface GameListPagingClient {
    @Data
    @NoArgsConstructor
    public class GameItem {
        private String gameCode;
        private Long detailGameSeq;
        private String detailGameKr;
        private String detailGameEn;
        private String statusCode;
        private String votingSchedule;
        private boolean groupGame;

        private int currentPage;
        private int previousPage;
        private int nextPage;

        public GameItem(String gameCode
                , Long detailGameSeq
                , String detailGameKr
                , String detailGameEn
                , String statusCode
                , Date startDate, Date endDate
                , boolean groupGame
                , int currentPage, int previousPage, int nextPage) {
            this.gameCode = gameCode;
            this.detailGameSeq = detailGameSeq;
            this.detailGameKr = detailGameKr;
            this.detailGameEn = detailGameEn;
            this.statusCode = statusCode;
            this.votingSchedule = DateUtil.format(startDate) +" ~ "+ DateUtil.format(endDate);
            this.groupGame = groupGame;
            this.currentPage = currentPage;
            this.previousPage = previousPage;
            this.nextPage = nextPage;
        }
    }
    public GameListPagingClient.GameItem requestGameListPgaing(String gameTypeCode, long gameId);
    public GameListPagingClient.GameItem requestGameListPgaing(String gameTypeCode, int page);
}
