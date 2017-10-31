package web.controller;

import core.model.Recommendation;
import core.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Alex on 10/31/2017.
 */
@RestController
public class PreloadController {

    @Autowired
    SkillService skillService;

    @Autowired
    ClientService clientService;

    @Autowired
    ProviderService providerService;

    @Autowired
    ManageService manageService;

    @Autowired
    FetchService fetchService;

    @Autowired
    RecommendationService recommendationService;

    @Autowired
    RequestService requestService;

    @Autowired
    ReviewService reviewService;

    @RequestMapping(value = "doPreload")
    public String preload()
    {
        return "TODO...";
    }
}
