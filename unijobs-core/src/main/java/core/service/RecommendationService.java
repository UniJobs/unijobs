package core.service;

import core.model.Recommendation;

import java.util.List;

/**
 * Created by Alex on 10/31/2017.
 */
public interface RecommendationService {
    List<Recommendation> getAll();
    List<Recommendation> getAllFromUser();
    List<Recommendation> getAllForUser();
    List<Recommendation> getAllToUser();

    Recommendation findOne(int id);
    void insert(Recommendation recommendation);
    void clear();
}
