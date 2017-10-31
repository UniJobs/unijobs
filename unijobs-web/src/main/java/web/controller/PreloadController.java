package web.controller;

import core.model.*;
import core.service.*;
import javafx.util.Builder;
import jdk.nashorn.internal.runtime.ParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
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


    @RequestMapping(value = "doPreload")
    @Transactional
    public String preload()
    {
        addSkills();
        addClients();
        addProviders();
        addAuthorities();
        return "Preload complete!";
    }

    @Transactional
    private void addAuthorities(){
        List<UniUser> allUsers = fetchService.getAllUsers();
        for (UniUser u: allUsers){
            Authority a = new Authority(u.getUsername(), "user");
            manageService.addAuthority(a);
        }
    }

    @Transactional
    private void addSkills(){
        List<Skill> skills = new ArrayList<>();
        skills.add(new Skill("Energy"));
        skills.add(new Skill("Attention"));
        skills.add(new Skill("Agility"));
        skills.add(new Skill("Stamina"));
        skills.add(new Skill("Passion"));

        skills.stream().forEach(s -> skillService.insert(s));
    }

    @Transactional
    private void addProviders(){
        String path = "D:\\ubb\\An 3 Semestrul 1\\UnijobsBackend\\unijobs\\preload_data\\Providers.csv";
        String testDate = "01-Ian-1910,13:00:14 PM";
        DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
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
    private void addClients() {
        String path = "D:\\ubb\\An 3 Semestrul 1\\UnijobsBackend\\unijobs\\preload_data\\Clients.csv";
        String testDate = "21-Apr-1900,13:00:14 PM";
        DateFormat formatter = new SimpleDateFormat("d-MMM-yyyy,HH:mm:ss aaa");
        Date date = new Date();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
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
