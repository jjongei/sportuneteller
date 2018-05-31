package com.sportuenteller.olympic.rest.rank.application;

import com.sportuenteller.olympic.common.code.StatusType;
import com.sportuenteller.olympic.games.vote.application.query.VoteTeamView;
import com.sportuenteller.olympic.games.vote.application.query.VoterView;
import com.sportuenteller.olympic.games.vote.application.query.VoterViewService;
import com.sportuenteller.olympic.games.vote.application.rank.MyStatusGame;
import com.sportuenteller.olympic.games.vote.application.rank.MyStatusGameService;
import com.sportuenteller.olympic.rest.auth.application.query.MemberView;
import com.sportuenteller.olympic.rest.auth.application.query.MemberViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestMyStatusService {
    @Autowired
    MyStatusGameService myStatusGameService;
    @Autowired
    VoterViewService voterViewService;
    @Autowired
    MemberViewService memberViewService;

    public MyStatusResult findMyStatus(String sessionKey){
        VoterView voter = voterViewService.findVoterView(sessionKey);

        if(voter == null){
           MemberView memberView = memberViewService.findMemberView(sessionKey);
            return new MyStatusResult(memberView.getName(), memberView.getUserId(), memberView.getSessionKey());
        }

        return  new MyStatusResult(voter.getName()
            , voter.getUserId()
            , sessionKey
            , voter.getPoint()
            , voter.getVoteCount()
            , voter.getGoldCount()
            , voter.getSilverCount()
            , voter.getBronzeCount()
            , convert(voter));
    }

    private List<MyStatusGameResult> convert(VoterView voterView){

        List<VoteTeamView> teamViews = voterView.getVoteTeams();

        if(teamViews != null){
            List<Long> idList = teamViews.stream()
                    .map(m -> m.getTeamId()).collect(Collectors.toList());

            List<MyStatusGame> myStatusGames = myStatusGameService.findMyStatusGame(idList);

            return myStatusGames.stream()
                    .map(m -> new MyStatusGameResult(m.getGameTypeCode().getCode()
                            , m.getGameId()
                            , m.getGameGameKr()
                            , m.getGameGameEn()
                            , m.isGroup()
                            , m.getStatusType() != null ? StatusType.possible.equals(m.getStatusType()) : false
                            , new MyStatusTeamResult(m.getTeamId()
                            , m.getCountryCode()
                            , m.getCountryNameKr()
                            , m.getCountryNameEn()
                            , m.getMedalCode().name()
                            , m.getPlayers() != null ? new ArrayList<String>(m.getPlayers()) : null))).collect(Collectors.toList());

        }

        return Collections.emptyList();

    }
}
