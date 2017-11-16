package web.controller;

import core.model.Authority;
import core.model.UniUser;
import core.service.AuthorityService;
import core.service.UniUserService;
import core.utils.MailUtils;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import web.dto.UniUserDTO;
import web.dtos.UniUsersDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/user")
public class UniUserController {
    private static final Logger log = LoggerFactory.getLogger(UniUserController.class);

    @Autowired
    UniUserService uniUserService;

    @Autowired
    AuthorityService authorityService;

    @RequestMapping(value="/users", method = RequestMethod.GET)
    public UniUsersDTO getUsers(){
        return new UniUsersDTO(uniUserService.getAllUsers());
    }

    @RequestMapping(value = "/getUserById")
    public UniUserDTO getUserById(@RequestParam("userId") Integer userId){
        log.trace("Get user by id : id={}",userId);
        UniUser uniUser = uniUserService.getUserById(userId);
        log.trace("user returned by id={} is user={}",userId,uniUser);
        return new UniUserDTO(uniUser);

    }

    @RequestMapping(value = "/getUserByName")
    @Transactional
    public UniUserDTO getUniUserByUsername(@RequestParam(value = "username", required = true) String username) {
        System.out.println("username" + username);
        UniUser u = uniUserService.getUserByUsername(username);
        UniUserDTO result = new UniUserDTO(u);
        System.out.println(result);
        return result;
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public UniUserDTO addUser(
            @RequestBody final UniUserDTO userDTO){

        log.trace("addUser: userDto={}", userDTO);
        UniUser user;
        //user = uniUserService.getUserByUsername(userDTO.getUsername());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        try {
            user = UniUser.builder()
                    .email(userDTO.getEmail())
                    .username(userDTO.getEmail())
                    .password(encoder.encode(userDTO.getPassword()))
                    .dob(null)
                    .firstname(userDTO.getFirstname())
                    .lastname(userDTO.getLastname())
                    .enabled(false)
                    .build();
            if(uniUserService.getUserByEmail(user.getEmail()) == null){
                uniUserService.addUser(user);
                String subject = "Unijobs Registration";
                String text = generateMail(user);
                MailUtils.sendMail(subject,text,user.getEmail());
            }
        } catch (DataIntegrityViolationException e) {
            user = UniUser.builder().build();
            log.trace("not added user={}", user);
        }
        return new UniUserDTO(user);
    }

    private String generateMail(UniUser user){
        StringBuilder message = new StringBuilder();
        message.append("Hello,\n").append(user.getFirstname()).append(" ").append(user.getLastname()).append("\n\n");
        message.append("Welcome To Unijobs").append("\n").append("Please click on the link below to validate your account");
        message.append("\n").append("http://localhost:8080/api/user/validate?token=").append(encrypt(user.getId()));
        message.append("\n\n").append("Have a great Day!").append("\n").append("Unijobs Team.");
        return message.toString();
    }

    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    public void validateUser(@RequestParam String token, HttpServletRequest request,
                                   HttpServletResponse response) throws IOException {
        UniUser user = uniUserService.getUserById(Integer.parseInt(decrypt(token)));
        Authority authority = new Authority(user.getUsername(),"USER");
        authorityService.addAuthority(authority);
        user.setEnabled(true);
        uniUserService.updateUser(user);
        request.setAttribute("user",new UniUserDTO(user));
        response.sendRedirect("http://localhost:4200");
    }


    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public UniUserDTO updateUser(
            @RequestBody final UniUserDTO userDTO){
        log.trace("updateUser: userId={}, userDTO={}", userDTO.getId(), userDTO);

        UniUser user;
        DateFormat receivedFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formatterDB = new SimpleDateFormat("d-MMM-yyyy");

        try {
            Date date = receivedFormat.parse(userDTO.getDob());
            String dbDate = formatterDB.format(date);
            user = UniUser.builder()
                    .id(userDTO.getId())
                    .email(userDTO.getEmail())
                    .username(userDTO.getEmail())
                    .password(userDTO.getPassword())
                    .dob(formatterDB.parse(dbDate))
                    .firstname(userDTO.getFirstname())
                    .lastname(userDTO.getLastname())
                    .phone(userDTO.getPhone())
                    .enabled(true)
                    .build();
            uniUserService.updateUser(user);
        } catch (DataIntegrityViolationException | ParseException e) {
            user = UniUser.builder().build();
            log.trace("not updated user={}", user);
        }
        return new UniUserDTO(user);
    }

    private String encrypt(Integer number) {
        try {
            return java.util.Base64.getUrlEncoder().encodeToString(number.toString().getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "-7";
    }

    private String decrypt(String token){
        return new String(java.util.Base64.getUrlDecoder().decode(token), StandardCharsets.UTF_8);
    }

}
