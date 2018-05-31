package com.sportuenteller.olympic.common.excel.game;

import com.sportuenteller.olympic.common.utils.DateUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

@Data
@NoArgsConstructor
public class GameExcelRead {
    private long id;

    private String code;
    private String nameKr;
    private String nameEn;
    private Date gameStartDate;
    private Date gameEndDate;
    private Date voteStartDate;
    private Date voteEndDate;
    private boolean group;
    private boolean modify;

    public GameExcelRead(String id
            , String code
            , String nameKr
            , String nameEn
            , String gameStartDate
            , String gameEndDate
            , String voteStartDate
            , String voteEndDate
            , String group
            , String modify) {
        this.id = id != null ? Long.parseLong(id) : 0L;
        this.code = code;
        this.nameKr = nameKr;
        this.nameEn = nameEn;
        this.gameStartDate = DateUtil.parseDate(gameStartDate);
        this.gameEndDate = DateUtil.parseDate(gameEndDate);
        this.voteStartDate = DateUtil.parseDate(voteStartDate);
        this.voteEndDate = DateUtil.parseDate(voteEndDate);
        this.group = group != null && "T".equals(group);
        this.modify = modify != null && "T".equals(modify);
    }

}
