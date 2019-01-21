package core.service;

import core.model.Vote;

import java.sql.SQLException;

public interface VoteService {
    void addVote(Vote vote);
    void shuffle() throws SQLException;
}