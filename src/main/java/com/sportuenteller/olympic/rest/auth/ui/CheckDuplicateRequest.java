package com.sportuenteller.olympic.rest.auth.ui;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class CheckDuplicateRequest implements Serializable {
    private String userId;
}
