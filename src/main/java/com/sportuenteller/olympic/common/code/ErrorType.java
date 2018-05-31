package com.sportuenteller.olympic.common.code;

public enum ErrorType {
    login_failed("error.message.login.failed","로그인에 실패 했습니다.")
    ,not_signed("error.message.not.signed","로그인이 필요합니다.")
    ,user_already_exists("error.message.user.already.exists","사용자가 이미 존재 합니다.")
    ,user_register_failed("error.message.user.register.failed","사용자 등록 실패 했습니다.")
    ,error("error.systme","오류가 발생 했습니다.")
    ,no_user("error.no.user","사용자가 없습니다.")
    ;

    private String errorCode;
    private String errorMessage;

    private ErrorType(String errorCode, String errorMessage){
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode(){
        return this.errorCode;
    }

    public String getErrorMessage(){
        return this.errorMessage;
    }

}
