package web.security;

/**
 * Created by Ionut on 22/5/2017.
 */
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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