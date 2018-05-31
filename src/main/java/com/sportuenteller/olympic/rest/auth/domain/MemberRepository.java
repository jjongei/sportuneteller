package com.sportuenteller.olympic.rest.auth.domain;

import com.sportuenteller.olympic.common.code.MemberType;

public interface MemberRepository {
    public Member findBySessionKey(SessionKey sessionKey);
    public Member findByMemberTypeAndUserId(MemberType memberType, String userId);

    public void save(Member member);
    public void remove(Member member);

}
