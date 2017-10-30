package web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
public class OAuth2SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        //Here are some dummy user accounts with different roles to check the functionality of OAuth2.0
        BCryptPasswordEncoder encoder = passwordEncoder();
        /*auth.inMemoryAuthentication()
                .passwordEncoder(encoder)
                .withUser("vac.gelu").password(encoder.encode("12345")).roles("teacher").and()
                .withUser("forest").password(encoder.encode("12345")).roles("teacher").and()
                .withUser("ela.valimareanu").password(encoder.encode("12345")).roles("teacher").and()
                .withUser("mihis.andreea").password(encoder.encode("12345")).roles("teacher").and()
                .withUser("radu.gaceanu").password(encoder.encode("12345")).roles("teacher").and()
                .withUser("dan.chiorean").password(encoder.encode("12345")).roles("teacher").and()
                .withUser("mihoc.tudor").password(encoder.encode("12345")).roles("teacher").and()
                .withUser("mmie1942").password(encoder.encode("12345")).roles("student").and()
                .withUser("maie1949").password(encoder.encode("12345")).roles("student");*/
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(encoder)
                .usersByUsernameQuery(
                        "select username,password, enabled from users where username=?")
                .authoritiesByUsernameQuery(
                        "select username, role from user_roles where username=?");
        auth.inMemoryAuthentication().passwordEncoder(encoder).withUser("admin").password(encoder.encode("admin")).roles("ADMIN");
        String test = encoder.encode("1234");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .antMatchers("/jobs").permitAll()
                .antMatchers("/skills").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().httpBasic()
                .and().csrf().disable();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

    @Bean
    @Autowired
    public TokenStoreUserApprovalHandler userApprovalHandler(TokenStore tokenStore){
        TokenStoreUserApprovalHandler handler = new TokenStoreUserApprovalHandler();
        handler.setTokenStore(tokenStore);
        handler.setRequestFactory(new DefaultOAuth2RequestFactory(clientDetailsService));
        handler.setClientDetailsService(clientDetailsService);
        return handler;
    }

    @Bean
    @Autowired
    public ApprovalStore approvalStore(TokenStore tokenStore) throws Exception {
        TokenApprovalStore store = new TokenApprovalStore();
        store.setTokenStore(tokenStore);
        return store;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
