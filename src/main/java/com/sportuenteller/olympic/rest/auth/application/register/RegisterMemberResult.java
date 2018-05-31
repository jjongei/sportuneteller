package com.sportuenteller.olympic.rest.auth.application.register;

import com.sportuenteller.olympic.common.code.ErrorType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RegisterMemberResult implements Serializable {
    boolean register;
    String userId;
    String name;
    String sessionKey;
    String errorCode;

    protected RegisterMemberResult(String userId, String name, String sessionKey){
        this.register = true;
        this.userId = userId;
        this.name = name;
        this.sessionKey = sessionKey;
    }

    protected RegisterMemberResult(String userId, String name, ErrorType errorType){
        this.register = false;
        this.userId = userId;
        this.name = name;
        this.errorCode = errorType.getErrorCode();
    }

}
