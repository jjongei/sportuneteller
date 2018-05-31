package com.sportuenteller.olympic.rest.auth.application.register;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class RegisterMemberRequest implements Serializable {
    private String userId;
    private String name;
    private String password;
}

