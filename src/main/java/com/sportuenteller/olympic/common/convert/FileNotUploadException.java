package com.sportuenteller.olympic.common.convert;

import lombok.Getter;

@Getter
public class FileNotUploadException extends RuntimeException {
    private  String subject;

    public FileNotUploadException(Throwable cause, String subject) {
        super(cause);
        this.subject = subject;
    }
}
