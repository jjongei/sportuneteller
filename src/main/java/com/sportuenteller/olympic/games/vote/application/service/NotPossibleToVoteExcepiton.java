package com.sportuenteller.olympic.games.vote.application.service;

public class NotPossibleToVoteExcepiton extends RuntimeException {
    public NotPossibleToVoteExcepiton(String message) {
        super(message);
    }
}
