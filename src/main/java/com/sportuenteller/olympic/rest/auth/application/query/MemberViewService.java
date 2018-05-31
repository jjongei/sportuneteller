package com.sportuenteller.olympic.rest.auth.application.query;

import com.sportuenteller.olympic.rest.auth.application.helper.FindMemberHelper;
import com.sportuenteller.olympic.rest.auth.domain.Member;
import com.sportuenteller.olympic.rest.auth.domain.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberViewService {
    @Autowired
    FindMemberHelper findMemberHelper;
    @Autowired
    MemberRepository memberRepository;


    public MemberView findMemberView(String sessionKey){
        Member member = findMemberHelper.findMember(sessionKey, memberRepository);
        if(member != null){
            return new MemberView(member.getUserId(), member.getName(), member.getSessionKey().getValue());
        }
        return null;
    }
}
