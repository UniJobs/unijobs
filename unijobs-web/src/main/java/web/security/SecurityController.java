package web.security;

/**
 * Created by Ionut on 22/5/2017.
 */
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
}