package core.service;

import core.model.Vote;
import core.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VoteServiceImpl implements VoteService {

    private static final Logger log = LoggerFactory.getLogger(VoteService.class);

    @Autowired
    VoteRepository voteRepository;

    @Override
    @Transactional
    public void addVote(Vote vote) {
        log.trace("createVote: vote={}", vote);

        vote = voteRepository.save(vote);

        log.trace("createUser: user={}", vote);
    }
}