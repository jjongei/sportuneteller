package com.sportuenteller.olympic.rest.auth.ui;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class LoginRequest implements Serializable {
    private String userId;
    private String password;
    private String name;
}
