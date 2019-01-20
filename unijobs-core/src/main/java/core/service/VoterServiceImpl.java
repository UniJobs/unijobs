package core.service;

import core.model.Voter;
import core.repository.VoterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VoterServiceImpl implements VoterService {
    private static final Logger log = LoggerFactory.getLogger(VoterService.class);

    @Autowired
    VoterRepository voterRepository;


    @Override
    @Transactional
    public void addVoter(Voter voter) {
        log.trace("createVoter: voter={}", voter);

        voter = voterRepository.save(voter);

        log.trace("createVoter: voter={}", voter);
    }
}
