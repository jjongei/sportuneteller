package com.sportuenteller.olympic.games.vote.domain.voter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface VoterRepository {
    Voter findById(VoterId id);
    List<Voter> findList(VoterSearchRequest request);
    List<Voter> findList(VoterSearchRequest request, Sort sort);

    Page<Voter> findList(VoterSearchRequest request, Pageable pageable);

    void save(Voter voter);
    void remove(Voter voter);
}
