package ua.challenge.security.filters;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by d.bakal on 4/2/2016.
 *
 *  RESTful web сервис будет включать CORS заголовки контроля доступа в свой ответ
 *  SimpleCORSFilter отвечает на все запросы с определенными Access-Control-* заголовками.
 *  В данном случае, заголовки установлены на разрешение POST, GET, OPTIONS или DELETE запросов
 *  от клиентов любого хоста.
 *
 */
@Component
public class SimpleCORSFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 100);

        String secret = "key123";
        String jwt = Jwts.builder()
                .setSubject("den").setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        System.out.println("0 >>>>>>>>>>>>>>>>> request = " + request.getServletPath());

        if ("/country/get".equals(request.getServletPath())) {
            if (request.getCookies() != null) {
                Optional<Cookie> validToken = Arrays.stream(request.getCookies())
                        .filter(c -> "VALID-TOKEN".equals(c.getName())).findFirst();

                System.out.println("1 >>>>>>>>>>>>>>>>> request = " + request.getServletPath());

                if (validToken.isPresent()) {
                    System.out.println("2 >>>>>>>>>>>>>>>>> cookie = " + validToken.get().getValue());
                }
            }

            Cookie cookie = new Cookie("VALID-TOKEN", jwt);
            response.addCookie(cookie);

            request.getSession().setAttribute("VALID-TOKEN", jwt);
        }

        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
//        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setHeader("X-AUTH-TOKEN", jwt);
        chain.doFilter(req, res);
    }

    public void init(FilterConfig filterConfig) {}

    public void destroy() {}

}
