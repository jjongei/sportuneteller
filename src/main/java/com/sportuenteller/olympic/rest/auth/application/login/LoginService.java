package com.sportuenteller.olympic.rest.auth.application.login;

import com.sportuenteller.olympic.rest.auth.application.helper.MemberNotFoundException;
import com.sportuenteller.olympic.rest.auth.application.helper.FindMemberHelper;
import com.sportuenteller.olympic.rest.auth.domain.LoginFailedException;
import com.sportuenteller.olympic.rest.auth.domain.Member;
import com.sportuenteller.olympic.rest.auth.domain.MemberRepository;
import com.sportuenteller.olympic.rest.auth.domain.SessionKey;
import com.sportuenteller.olympic.common.code.ErrorType;
import com.sportuenteller.olympic.common.code.MemberType;
import com.sportuenteller.olympic.common.utils.EncAlgorithm;
import com.sportuenteller.olympic.common.utils.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class LoginService{

    @Autowired
    protected FindMemberHelper findMemberHelper;

    @Autowired
    MemberRepository memberRepository;

    public LoginResult login(String userId, String password) {
        Member member = null;

        try {
            member = this.findMemberHelper.findMember(MemberType.general, userId, this.memberRepository);
        } catch (MemberNotFoundException e) {
            return new LoginResult(userId, ErrorType.no_user);
        } catch (RuntimeException e) {
            return new LoginResult(userId, ErrorType.error);
        }

        try{
            member.isAuthByGeneral(password);
        }catch (LoginFailedException e){
            return new LoginResult(userId);
        }
        return new LoginResult(member.getUserId(), member.getName(), member.getSessionKey().getValue());
    }

    @Transactional
    public LoginResult login(MemberType memberType, String userId, String name) {
        Member member = null;
        try {
            member = this.findMemberHelper.findMember(memberType, userId, this.memberRepository);
        } catch (MemberNotFoundException e) {
            member = new Member(new SessionKey(KeyGenerator.uuidHash(EncAlgorithm.SHA256))
                    , userId, name, memberType);
            this.memberRepository.save(member);
        } catch (RuntimeException e) {
            return new LoginResult(userId, ErrorType.error);
        }
        try{
            member.isAuthByFacebookOrGoogle();
        }catch (LoginFailedException e){
            return new LoginResult(userId);
        }
        return new LoginResult(member.getUserId(), member.getName(), member.getSessionKey().getValue());
    }

    public CheckLoginResult checkLogin(String sessionKey) {
        Member member = null;
        try{
            member = this.findMemberHelper.findMember(sessionKey, this.memberRepository);
        }catch (MemberNotFoundException e){
            return new CheckLoginResult(sessionKey, ErrorType.not_signed);
        }
        return member.checkLogin()
                ? new CheckLoginResult(member.getUserId(),member.getName(),member.getSessionKey().getValue())
                : new CheckLoginResult(sessionKey);
    }


}
