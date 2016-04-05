package ua.challenge.security.authentication.jwt.first;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestTokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public RestTokenAuthenticationFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        BearerTokenExtractor tokenExtractor = new BearerTokenExtractor();
        Authentication authentication = tokenExtractor.extract(request);
        System.out.println("authentication213 = " + authentication);
        /*if (token == null)
            token = request.getParameter("token");
        if (token == null) {
            return getAuthenticationManager().authenticate(new TokenAuthentication(null));
        }*/
        TokenAuthentication tokenAuthentication = new TokenAuthentication((String) authentication.getPrincipal());
        return getAuthenticationManager().authenticate(tokenAuthentication);
    }
}
