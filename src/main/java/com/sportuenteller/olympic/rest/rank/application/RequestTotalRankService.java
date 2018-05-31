package com.sportuenteller.olympic.rest.rank.application;

import com.sportuenteller.olympic.games.vote.application.rank.TotalRank;
import com.sportuenteller.olympic.games.vote.application.rank.TotalRankService;
import com.sportuenteller.olympic.rest.auth.application.query.MemberView;
import com.sportuenteller.olympic.rest.auth.application.query.MemberViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestTotalRankService {
    @Autowired
    TotalRankService  totalRankService;
    @Autowired
    MemberViewService memberViewService;

    public TotalRankResult findTotalRankList(String sessionKey){
        MemberView memberView = memberViewService.findMemberView(sessionKey);
        List<TotalRank> pointRanks = totalRankService.findTotalRanByPoint(sessionKey);
        List<TotalRank> medalRanks = totalRankService.findTotalRanByMedal(sessionKey);

        return new TotalRankResult(memberView.getUserId(), memberView.getSessionKey()
                , pointRanks.stream()
                    .map(m -> new TotalRankList(m.getRownum()
                            , m.getUserId()
                            , m.getName()
                            , m.getSessionKey()
                            , m.isMyrank()
                            , m.getPoint())).collect(Collectors.toList())

                , medalRanks.stream()
                    .map(m -> new TotalRankList(m.getRownum()
                            , m.getUserId()
                            , m.getName()
                            , m.getSessionKey()
                            , m.isMyrank()
                            , m.getGoldCount()
                            , m.getSilverCount()
                            , m.getBronzeCount()
                            , m.getTotalCount())).collect(Collectors.toList()));
    }
}
