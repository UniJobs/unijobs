package web.controller;

import core.model.*;
import core.service.*;
import javafx.util.Builder;
import jdk.nashorn.internal.runtime.ParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.security.x509.UniqueIdentity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Alex on 10/31/2017.
 */
@RestController
@RequestMapping("/api")
public class PreloadController {

    private static final String dataPath = "D:\\ubb\\An 3Semestrul 1\\UnijobsBackend\\unijobs\\";
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

    @Autowired
    AuthorityService authorityService;

    private BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();

    /*
        !!! README !!!
        preload is secured. To access the endpoints you will have to call the initAdmin first.
        This will create one account {username:"admin", password:"admin"},
        then log as an administrator and access the api/preload/doPreload or api/preload/clear apis.
        Pay attention the initAdmin url is api/initAdmin.
     */
    @RequestMapping(value = "/initAdmin", method = RequestMethod.GET)
    @Transactional
    public String addAdmin(){
        UniUser admin = UniUser.builder().username("admin").password(encoder.encode("admin")).enabled(true).build();
        manageService.addUser(admin);
        Authority authority = new Authority("admin","ADMIN");
        authorityService.addAuthority(authority);
        return "Done";
    }

    @RequestMapping(value = "/preload/doPreload", method = RequestMethod.POST)
    @Transactional
    public String preload()
    {
        addSkills();
        addClients();
        addProviders();
        addAuthorities();
        return "Preload complete!";
    }

    @RequestMapping(value ="/preload/clearDB",method = RequestMethod.POST)
    @Transactional
    public String clearDB(){
        authorityService.clear();
        manageService.clearJobs();
        skillService.clear();
        clientService.clear();
        providerService.clear();
        return "DB clear";
    }

    @Transactional
    void addAuthorities(){
        //Get all users expect the admin
        List<UniUser> allUsers = fetchService.getAllUsers().stream()
                .filter(u -> !u.getUsername().equals("admin")).collect(Collectors.toList());
        Authority authority;
        for (UniUser u: allUsers){
            authority = new Authority(u.getUsername(), "user");
            authorityService.addAuthority(authority);
        }
    }

    @Transactional
    void addSkills(){
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("Energy"));
        skills.add(new Skill("Attention"));
        skills.add(new Skill("Agility"));
        skills.add(new Skill("Stamina"));
        skills.add(new Skill("Passion"));

        skills.forEach(s -> skillService.insert(s));
    }

    @Transactional
    void addProviders(){
        String path = "../../preload_data/Providers.csv";
        String testDate = "01-Ian-1910,13:00:14 PM";
        DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
        Date date = new Date();
        try{
            List<String> lines = Files.lines(Paths.get(path)).collect(Collectors.toList());
            for (String l: lines){
                String[] parts = l.split(",");
                Provider p = new Provider(parts[0], encoder.encode(parts[1]), parts[2], parts[3], parts[4], date);
                providerService.insert(p);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Transactional
    void addClients() {
        String path = "../../preload_data/Clients.csv";
        String testDate = "21-Apr-1900,13:00:14 PM";
        DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
        Date date = new Date();
        try {
            List<String> lines = Files.lines(Paths.get(path)).collect(Collectors.toList());
            for (String l : lines) {
                String[] parts = l.split(",");
                try {
                    Client c = new Client(parts[0], encoder.encode(parts[1]), parts[2], parts[3], parts[4], date);
                    clientService.insert(c);
                    Integer nrJobs = Integer.parseInt(parts[6]);
                    int count = 1;
                    for (int i = 0; i < nrJobs; i++) {
                        Job j = new Job(parts[count + 6], parts[count + 7], Integer.parseInt(parts[count + 8]), Integer.parseInt(parts[count+9]));
                        j.setClient(c);
                        manageService.addJob(j);
                        count += 4;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
