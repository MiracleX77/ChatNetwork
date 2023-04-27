package ChatNetwork.ChatNetwork.config.token;

import ChatNetwork.ChatNetwork.service.TokenService;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TokenFilter extends GenericFilterBean {
    private final TokenService tokenService;

    public TokenFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        String authorization = request1.getHeader("Authorization");
        if(ObjectUtils.isEmpty(authorization)){
            chain.doFilter(request,response);
            return;
        }
        if(!authorization.startsWith("Bearer ")){
            chain.doFilter(request,response);
            return;
        }
        String token = authorization.substring(7);
        DecodedJWT decodedJWT = tokenService.verify(token);
        if(decodedJWT==null){
            chain.doFilter(request,response);
            return;
        }
        Long principal = decodedJWT.getClaim("principal").asLong();
        String role = decodedJWT.getClaim("role").asString();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal,"(protected)",authorities);

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authenticationToken);
        chain.doFilter(request,response);

    }
}
