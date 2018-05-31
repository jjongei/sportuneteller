package com.sportuenteller.olympic.rest.auth.application.login;

import com.sportuenteller.olympic.common.code.ErrorType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class CheckLoginResult implements Serializable {
    boolean signing;
    String userId;
    String name;
    String sessionKey;
    String errorCode;

    protected CheckLoginResult(String userId, String name, String sessionKey) {
        this.signing = true;
        this.userId = userId;
        this.name = name;
        this.sessionKey = sessionKey;
    }

    protected CheckLoginResult(String sessionKey){
        this(sessionKey, ErrorType.not_signed);
    }

    protected CheckLoginResult(String sessionKey, ErrorType errorType){
        this.signing = false;
        this.sessionKey = sessionKey;
        this.errorCode = errorType.getErrorCode();
    }
}
