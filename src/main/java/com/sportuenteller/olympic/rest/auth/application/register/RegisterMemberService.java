package com.sportuenteller.olympic.rest.auth.application.register;

import com.sportuenteller.olympic.rest.auth.application.helper.MemberNotFoundException;
import com.sportuenteller.olympic.rest.auth.application.helper.FindMemberHelper;
import com.sportuenteller.olympic.rest.auth.domain.Member;
import com.sportuenteller.olympic.rest.auth.domain.MemberRepository;
import com.sportuenteller.olympic.rest.auth.domain.SessionKey;
import com.sportuenteller.olympic.common.code.ErrorType;
import com.sportuenteller.olympic.common.code.MemberType;
import com.sportuenteller.olympic.common.utils.EncAlgorithm;
import com.sportuenteller.olympic.common.utils.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

@Service
public class RegisterMemberService {

    @Autowired
    protected FindMemberHelper findMemberHelper;

    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public RegisterMemberResult registerMember(RegisterMemberRequest request){
        Member member = null;
        try {
            Member checkMember = this.findMemberHelper.findMember(MemberType.general, request.getUserId(), this.memberRepository);
            if(checkMember != null
                    && checkMember.getSessionKey() != null
                    && StringUtils.hasText(checkMember.getSessionKey().getValue())){
                return new RegisterMemberResult(request.getUserId(), request.getName(), ErrorType.user_already_exists);
            }
        }catch (MemberNotFoundException e) {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String password = encoder.encode(request.getPassword());

            member =  new Member(new SessionKey(KeyGenerator.uuidHash(EncAlgorithm.SHA256))
                    , request.getUserId(), request.getName(), password, MemberType.general);


            this.memberRepository.save(member);

        } catch (Exception e) {
            return new RegisterMemberResult(request.getUserId(), request.getName(), ErrorType.user_register_failed);
        }

        return new RegisterMemberResult(member.getUserId(), member.getName(), member.getSessionKey().getValue());
    }

    public CheckDuplicateResult checkDuplicate(String userId){
        Member member = null;
        try {
            member = this.findMemberHelper.findMember(MemberType.general, userId, this.memberRepository);
        } catch (MemberNotFoundException e) {
            return new CheckDuplicateResult(userId);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new CheckDuplicateResult(userId, ErrorType.error);
        }
        if(member != null
                && member.getSessionKey() != null
                && StringUtils.hasText(member.getSessionKey().getValue())){

            return new CheckDuplicateResult(userId, ErrorType.user_already_exists);
        }
        return new CheckDuplicateResult(userId, ErrorType.error);
    }
}
