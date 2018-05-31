package com.sportuenteller.olympic.rest.auth.application.helper;

import com.sportuenteller.olympic.rest.auth.domain.Member;
import com.sportuenteller.olympic.rest.auth.domain.MemberRepository;
import com.sportuenteller.olympic.rest.auth.domain.SessionKey;
import com.sportuenteller.olympic.common.code.MemberType;
import org.springframework.stereotype.Component;

@Component
public class FindMemberHelper {

    public Member findMember(String sessionKey, MemberRepository repository){
        Member member = repository.findBySessionKey(new SessionKey(sessionKey));
        if(member == null){
            throw new MemberNotFoundException();
        }
        return member;
    }

    public Member findMember(MemberType memberType, String userId, MemberRepository repository){

        Member member = null;
        try {
            member = repository.findByMemberTypeAndUserId(memberType, userId);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        if(member == null){
            throw new MemberNotFoundException();
        }
        return member;
    }
}
