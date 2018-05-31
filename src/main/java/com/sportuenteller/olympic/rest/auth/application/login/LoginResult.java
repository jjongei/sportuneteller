package com.sportuenteller.olympic.rest.auth.application.login;

import com.sportuenteller.olympic.common.code.ErrorType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class LoginResult implements Serializable {
    boolean access;

    String userId;
    String name;
    String sessionKey;
    String errorCode;

    protected LoginResult(String userId, String name, String sessionKey) {
        this.access = true;
        this.userId = userId;
        this.name = name;
        this.sessionKey = sessionKey;
    }

    protected LoginResult(String userId){
        this(userId, ErrorType.login_failed);
    }

    protected LoginResult(String userId, ErrorType errorType){
        this.access = false;
        this.userId = userId;
        this.errorCode = errorType.getErrorCode();
    }
}
