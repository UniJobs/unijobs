package web.controller;

import core.model.Client;
import core.model.Recommendation;
import core.model.Skill;
import core.service.*;
import javafx.util.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Transactional
    public String preload()
    {
        List<Skill> skills = makeSkills();
        List<Client> clients = makeClients();

        for (Skill s: skills){
            skillService.insert(s);
        }

        for (Client c: clients){
            clientService.insert(c);
        }
        return "Preload complete!";
    }

    List<Skill> makeSkills(){
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("C++"));
        skills.add(new Skill("Python"));
        skills.add(new Skill("C#"));
        skills.add(new Skill("Java"));
        skills.add(new Skill("Javascript"));
        skills.add(new Skill("Reverse Engineering"));
        skills.add(new Skill("Malware Analysis"));
        skills.add(new Skill("AI"));
        skills.add(new Skill("Prolog"));
        skills.add(new Skill("Ocaml"));
        skills.add(new Skill("Android"));
        return skills;
    }

    List<Client> makeClients(){
        List<Client> clients = new ArrayList<>();
        String testDate = "29-Apr-2010,13:00:14 PM";
        DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
        Date date = new Date();
        try {
            date = formatter.parse(testDate);
        }
        catch (Exception e){
            return null;
        }
        clients.add(new Client("mfie1944", "admin", "mfie1994@scs.ubbcluj.ro", "Alex", "Matica", date));
        clients.add(new Client("abcd1234", "admin", "abcd@scs.ubbcluj.ro", "Mihai", "Bobina", date));
        clients.add(new Client("efgh5678", "admin", "efgh5678@scs.ubbcluj.ro", "Bait", "Beight", date));
        clients.add(new Client("ijkl9999", "admin", "ijkl9999@scs.ubbcluj.ro", "Bob", "Bobby", date));
        clients.add(new Client("asda4354", "admin", "asda4354@scs.ubbcluj.ro", "Andrei", "John", date));
        clients.add(new Client("bsda1093", "admin", "bsda1093@scs.ubbcluj.ro", "Bill", "Clinton", date));
        clients.add(new Client("gggg0000", "admin", "gggg0000@scs.ubbcluj.ro", "Martin", "Freeman", date));
        clients.add(new Client("xxxx6666", "admin", "xxxx6666@scs.ubbcluj.ro", "Elisei", "Dragoslav", date));
        clients.add(new Client("maie1949", "admin", "maie1949@scs.ubbcluj.ro", "Aris", "Miuta", date));
        return clients;
    }
}
