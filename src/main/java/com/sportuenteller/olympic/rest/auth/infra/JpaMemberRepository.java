package com.sportuenteller.olympic.rest.auth.infra;

import com.sportuenteller.olympic.rest.auth.domain.Member;
import com.sportuenteller.olympic.rest.auth.domain.MemberRepository;
import com.sportuenteller.olympic.rest.auth.domain.QMember;
import com.sportuenteller.olympic.rest.auth.domain.SessionKey;
import com.sportuenteller.olympic.common.code.MemberType;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class JpaMemberRepository extends QueryDslRepositorySupport implements MemberRepository {

    public JpaMemberRepository() {
        super(Member.class);
    }


    @Override
    public Member findBySessionKey(SessionKey sessionKey) {
        QMember qMember = QMember.member;
        return this.from(qMember).where(qMember.sessionKey.eq(sessionKey)).fetchOne();
    }

    @Override
    public Member findByMemberTypeAndUserId(MemberType memberType, String userId) {
        QMember qMember = QMember.member;
        return this.from(qMember).where(qMember.memberType.eq(memberType).and(qMember.userId.eq(userId))).fetchOne();
    }

    @Override
    public void save(Member member) {
        this.getEntityManager().persist(member);
    }

    @Override
    public void remove(Member member) {
        this.getEntityManager().remove(member);
    }
}
