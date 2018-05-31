package com.sportuenteller.olympic.rest.games.applicaiton.view;

import com.sportuenteller.olympic.common.utils.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

public interface GameViewListClient {
    @Data
    @NoArgsConstructor
    public class GameItem {
        private String gameCode;
        private Long detailGameSeq;
        private String detailGameKr;
        private String detailGameEn;
        private String statusCode;
        private String votingSchedule;


        public GameItem(String gameCode, Long detailGameSeq, String detailGameKr, String detailGameEn, String statusCode, Date startDate, Date endDate) {
            this.gameCode = gameCode;
            this.detailGameSeq = detailGameSeq;
            this.detailGameKr = detailGameKr;
            this.detailGameEn = detailGameEn;
            this.statusCode = statusCode;
            this.votingSchedule = DateUtil.format(startDate) +" ~ "+ DateUtil.format(endDate);
        }
    }
    public List<GameItem> requestGameViewList(String gameTypeCode);
}
