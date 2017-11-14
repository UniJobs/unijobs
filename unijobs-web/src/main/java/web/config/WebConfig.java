package web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Maniga on 4/7/2017.
 */
@Configuration
@EnableWebMvc

//TODO: add extra components
@ComponentScan({"web.config","web.security"})

public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/oauth/token").allowedOrigins("*");
                registry.addMapping("/**")
                        .allowedMethods(HttpMethod.OPTIONS.name(),
                                HttpMethod.PATCH.name(),
                                HttpMethod.PUT.name(),
                                HttpMethod.DELETE.name(),
                                HttpMethod.GET.name(),
                                HttpMethod.POST.name())
                        .allowedOrigins("*")
                        .allowCredentials(true)
                        .maxAge(360);
            }
        };
    }
}
