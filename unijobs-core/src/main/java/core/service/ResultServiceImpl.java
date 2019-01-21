package core.service;

import core.model.Result;
import core.repository.ResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResultServiceImpl implements ResultService{

    private static final Logger log = LoggerFactory.getLogger(ResultService.class);

    @Autowired
    ResultRepository resultRepository;

    @Override
    @Transactional
    public void addResult(Result result) {
        log.trace("createResult: result={}", result);

        result = resultRepository.save(result);

        log.trace("createResult: result={}", result);
    }
}
