package com.sportuenteller.olympic.rest.auth.domain;


import com.sportuenteller.olympic.common.code.MemberType;
import com.sportuenteller.olympic.common.utils.DateUtil;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@Access(AccessType.FIELD)
@Table(name="member")
public class Member {
    @EmbeddedId
    private SessionKey sessionKey;

    @Column(name = "user_id", columnDefinition = "nvarchar(128)", nullable = false)
    private String userId;

    @Column(name = "name", columnDefinition = "nvarchar(128)", nullable = false)
    private String name;

    @Column(name = "password", columnDefinition = "nvarchar(128)")
    private String password;

    @Column(name = "member_type", columnDefinition = "nvarchar(32)", nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    @Column(name="logining_flag", columnDefinition="char(1)")
    private Boolean logining;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date", columnDefinition = "datetime", nullable = false)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified_date", columnDefinition = "datetime", nullable = false)
    private Date lastModifiedDate;

    protected Member(){}

    public Member(SessionKey sessionKey, String userId, String name, String password, MemberType memberType) {
        this(sessionKey, userId, name, memberType);
        this.setPassword(password);
    }

    public Member(SessionKey sessionKey, String userId, String name, MemberType memberType) {
        this.setSessionKey(sessionKey);
        this.setUserId(userId);
        this.setName(name);

        this.setMemberType(memberType);

        this.logining = true;
        this.createDate = DateUtil.getCurrentDate();
        this.lastModifiedDate = DateUtil.getCurrentDate();
    }
    public boolean isAuthByGeneral(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!this.memberType.equals(MemberType.general) || !encoder.matches(password, this.password)){
            throw new LoginFailedException();
        }
        this.changeLogining(true);
        return true;
    }

    public boolean isAuthByFacebookOrGoogle(){
        if(this.memberType.equals(MemberType.general)){
            throw new LoginFailedException();
        }
        this.changeLogining(true);
        return true;

    }
    public void logout(){
        this.changeLogining(false);
    }
    public boolean checkLogin(){
        return this.logining;
    }


    private void changeLogining(boolean logining){
        this.logining = logining;
        this.lastModifiedDate = DateUtil.getCurrentDate();
    }


    private void setSessionKey(SessionKey sessionKey) {
        if(sessionKey == null) throw new IllegalArgumentException("Session Key");
        this.sessionKey = sessionKey;
    }

    private void setUserId(String userId) {
        if(userId == null) throw new IllegalArgumentException("User Id");
        this.userId = userId;
    }

    private void setName(String name) {
        if(name == null) throw new IllegalArgumentException("Name");
        this.name = name;
    }

    private void setPassword(String password) {
        if(password == null) throw new IllegalArgumentException("Password");
        this.password = password;
    }

    private void setMemberType(MemberType memberType) {
        if(memberType == null) throw new IllegalArgumentException("Member Type");
        this.memberType = memberType;
    }
}
