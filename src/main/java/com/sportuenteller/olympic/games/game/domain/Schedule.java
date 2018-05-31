package com.sportuenteller.olympic.games.game.domain;

import com.sportuenteller.olympic.common.code.StatusType;
import com.sportuenteller.olympic.common.utils.DateUtil;
import lombok.Getter;
import org.apache.commons.lang3.time.DateUtils;

import javax.persistence.*;
import java.util.Date;

@Getter
@Embeddable
@Access(AccessType.FIELD)
public class Schedule {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date", columnDefinition = "datetime", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date", columnDefinition = "datetime", nullable = false)
    private Date endDate;

    protected Schedule(){}

    public Schedule(Date startDate, Date endDate) {
        this.setStartDate(startDate);
        this.setEndDate(endDate);
    }

    public void setStartDate(Date startDate) {
        if(startDate == null) throw new IllegalArgumentException("Start Date");
        this.startDate = DateUtil.initDateByFrom(startDate);
    }

    public void setEndDate(Date endDate) {
        if(endDate == null) throw new IllegalArgumentException("End Date");
        this.endDate = DateUtil.initDateByTo(endDate);
    }

    /**
     * Schedule를 이용해서 경기 투표 상태를 계산한다.
     * @return
     */
    protected StatusType calculatorStatus(){
        Date currentDate = DateUtil.getCurrentDate();

        if(DateUtil.isValidDateRange(currentDate, this.startDate)){
            return StatusType.wait;
        }else if(DateUtil.isValidDateRange(this.startDate, currentDate) && DateUtil.isValidDateRange(currentDate, this.endDate)){
            return StatusType.possible;
        }else if(DateUtil.isValidDateRange(this.endDate, currentDate)){
            return StatusType.termination;
        }

        return StatusType.wait;
    }

    protected Schedule createTempSchedule(){
        return new Schedule(DateUtils.addDays(this.startDate , -3), DateUtils.addDays(this.startDate, -1));
    }
}
