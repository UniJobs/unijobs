package web.security;

/**
 * Created by Ionut on 22/5/2017.
 */
import core.model.TemporaryUser;
import core.model.UniUser;
import core.service.UniUserService;
import core.utils.MailUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Controller
public class SecurityController {
    public class Response {
        private String response;

        public Response(String response) {
            this.response = response;
        }

        public String getResponse() {
            return this.response;
        }

        public void setResponse(String response) {
            this.response = response;
        }
    }

   @Autowired
    UniUserService uniUserService;

    @RequestMapping(value = "/api/username", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Response currentUserName(Authentication authentication) {
        return new Response(authentication.getName());
    }

    @RequestMapping(value = "/api/role", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Response currentRole(Authentication authentication) {
        return new Response(authentication.getAuthorities().toString());
    }

    @RequestMapping(value = "/api/registerUser", method = RequestMethod.POST)
    public String registerUser(@RequestBody final TemporaryUser temporaryUser){

        //Check for duplicates
        List<UniUser> users = uniUserService.getAllUsers();
        UniUser u = null;
        for (int i = 0; i < users.size(); i++) {
            u = users.get(i);
            if (u.getUsername().equals(temporaryUser.getUsername())) {
                return "Something went wrong!";
            }
        }

        //save user until validation
        uniUserService.addTemporaryUser(temporaryUser);

        //Send mail
        String subject = "Unijobs Acount validation !\n";
        String message = "Hello " + temporaryUser.getUsername() + "!\n Please click on the link below to activate your acount:\n";
        try {
            message += "http://localhost:8080/validate?token=" + encrypt(temporaryUser.getId());
        } catch (UnsupportedEncodingException e) {
            message += "http://wops.somehtingnotworking.com";
        }
        message += "\n\n Have a Great day,\nUnijobs Team.";
        MailUtils.sendMail(subject, message, temporaryUser.getEmail());

        return "confirmMail";
    }

    @RequestMapping("/api/validate")
    public String validateUser(@RequestParam String token){
        try{
            TemporaryUser temporaryUser = uniUserService.getTemporaryUserById((long) Integer.parseInt(decrypt(token)));
            UniUser user = temporaryUser.toUser();
            uniUserService.addUser(user);
            uniUserService.removeTemporaryUser(temporaryUser);
            return "You have registereded";
        }catch (Exception ex){
            return "Something went wrong when validating account!";
        }
    }

    private String encrypt(Integer number) throws UnsupportedEncodingException {
        return Base64.getUrlEncoder().encodeToString(number.toString().getBytes("utf-8"));
    }

    private String decrypt(String token){
        return new String(Base64.getUrlDecoder().decode(token), StandardCharsets.UTF_8);
    }
}