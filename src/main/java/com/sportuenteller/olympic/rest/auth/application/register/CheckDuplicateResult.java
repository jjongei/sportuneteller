package com.sportuenteller.olympic.rest.auth.application.register;


import com.sportuenteller.olympic.common.code.ErrorType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CheckDuplicateResult implements Serializable {
    private boolean duplicate;
    String userId;
    String errorCode;

    protected CheckDuplicateResult(String userId){
        this.duplicate = false;
        this.userId = userId;
    }

    protected CheckDuplicateResult(String userId, ErrorType errorType){
        this.duplicate = true;
        this.userId = userId;
        this.errorCode = errorType.getErrorCode();
    }
}
