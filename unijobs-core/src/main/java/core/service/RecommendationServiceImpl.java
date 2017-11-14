package core.service;

import core.model.Recommendation;
import core.repository.RecommendationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Alex on 10/31/2017.
 */
@Service
public class RecommendationServiceImpl implements RecommendationService{

    private static final Logger log = LoggerFactory.getLogger(RecommendationService.class);

    @Autowired
    private RecommendationRepository recommendationRepository;

    @Override
    public List<Recommendation> getAll() {
        log.trace("recommendation service - get all");
        List<Recommendation> res = recommendationRepository.findAll();
        log.trace("recommendation service - got them all");
        return res;
    }

    @Override
    public List<Recommendation> getAllFromUser() {
        return null;
    }

    @Override
    public List<Recommendation> getAllForUser() {
        return null;
    }

    @Override
    public List<Recommendation> getAllToUser() {
        return null;
    }

    @Override
    public Recommendation findOne(int id) {
        log.trace("recommendation service - find one {}", id);
        Recommendation res = recommendationRepository.findOne(id);
        log.trace("recommendation service - found him");
        return res;
    }

    @Override
    public void insert(Recommendation recommendation) {
        log.trace("recommendation service - insert one {}", recommendation);
        recommendationRepository.save(recommendation);
        log.trace("recommednation service - inserted one");
    }

    @Override
    public void clear() {
        log.trace("clear Recommendations");
        recommendationRepository.deleteAll();
        log.trace("recommendations cleared");
    }
}
