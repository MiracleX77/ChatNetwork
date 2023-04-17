package ChatNetwork.ChatNetwork.config.token;

import ChatNetwork.ChatNetwork.service.TokenService;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class TokenFilterConfiguerer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private  final TokenService service;


    public TokenFilterConfiguerer(TokenService service) {
        this.service = service;
    }
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception{
        TokenFilter filter = new TokenFilter(service);
        httpSecurity.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
