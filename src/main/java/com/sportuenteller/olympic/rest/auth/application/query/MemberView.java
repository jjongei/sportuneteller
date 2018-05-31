package com.sportuenteller.olympic.rest.auth.application.query;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberView {
    String userId;
    String name;
    String sessionKey;

    public MemberView(String userId, String name, String sessionKey) {
        this.userId = userId;
        this.name = name;
        this.sessionKey = sessionKey;
    }
}
