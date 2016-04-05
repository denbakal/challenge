package ua.challenge.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import ua.challenge.security.authentication.jwt.first.RestTokenAuthenticationFilter;
import ua.challenge.security.authentication.jwt.first.TokenAuthenticationManager;
import ua.challenge.technicalrex.StatelessAuthenticationFilter;
import ua.challenge.technicalrex.TokenAuthenticationService;
import ua.challenge.technicalrex.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by d.bakal on 3/12/2016.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${current.realm}")
    private String realm;

    @Value("${digest.key}")
    private String key;

    @Value("${nonce.validity.seconds}")
    private int nonceValiditySeconds;

    /*private final UserService userService;
    private final TokenAuthenticationService tokenAuthenticationService;

    public WebSecurityConfig() {
        super(true);
        this.userService = new UserService();

        GrantedAuthority role = new SimpleGrantedAuthority("USER");
        Collection<GrantedAuthority> roles = new ArrayList<>();
        roles.add(role);

        this.userService.addUser(new User("den", "123123", roles));

        tokenAuthenticationService = new TokenAuthenticationService("tooManySecrets", userService);
    }*/

    /* In Memory Authentication */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
//        auth.inMemoryAuthentication().withUser("den").password("123456").roles("ADMIN");
        auth.userDetailsService(userDetailsService());
    }

    /* HTTP Basic Authentication */
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/hello").permitAll()
                .anyRequest().authenticated()
                .and()
            .httpBasic()
                .realmName(realm);
    }*/

    /* HTTP Digest Authentication */
    /*@Override
     protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                .authenticationEntryPoint(digestEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers("/", "/hello").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(digestAuthenticationFilter(digestEntryPoint()));
    }*/

    /* Form-Based Authentication */
    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
       http
            .authorizeRequests()
               .antMatchers("/", "/home", "/js*//*.js").permitAll()
               .anyRequest().authenticated()
               .and()
            .formLogin()
               .permitAll()
               .and()
            .logout()
                .permitAll();
    }*/

    /* JWT Authentication */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilterAfter(restTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .anonymous().and()
                .authorizeRequests()
                .antMatchers("/", "/login", "/**/*.html", "/**/*.css", "/**/*.js").permitAll()
                .anyRequest().authenticated().and()
                .formLogin().loginProcessingUrl("/login").passwordParameter("password").usernameParameter("username").permitAll()
                ;
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }*/

    @Bean
    @Override
    public UserService userDetailsService() {
        UserService userService = new UserService();

        GrantedAuthority role = new SimpleGrantedAuthority("USER");
        Collection<GrantedAuthority> roles = new ArrayList<>();
        roles.add(role);

        userService.addUser(new User("den", "123123", roles));
        return userService;
    }

    @Autowired
    TokenAuthenticationManager tokenAuthenticationManager;

    @Bean(name = "restTokenAuthenticationFilter")
    public RestTokenAuthenticationFilter restTokenAuthenticationFilter() {
        RestTokenAuthenticationFilter restTokenAuthenticationFilter = new RestTokenAuthenticationFilter("/rest/**");
        restTokenAuthenticationFilter.setAuthenticationManager(tokenAuthenticationManager);
        return restTokenAuthenticationFilter;
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    public UserService userDetailsService() {
        return userService;
    }

    @Bean
    public TokenAuthenticationService tokenAuthenticationService() {
        return tokenAuthenticationService;
    }*/

    /*00000000000000000000000*/

    /*@Autowired
    TokenAuthenticationManager tokenAuthenticationManager;

    @Bean(name = "restTokenAuthenticationFilter")
    public RestTokenAuthenticationFilter restTokenAuthenticationFilter() {
        RestTokenAuthenticationFilter restTokenAuthenticationFilter = new RestTokenAuthenticationFilter("*//**//**");
        restTokenAuthenticationFilter.setAuthenticationManager(tokenAuthenticationManager);
        return restTokenAuthenticationFilter;
    }*/

    /*@Override
    @Bean
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    public DigestAuthenticationFilter digestAuthenticationFilter(DigestAuthenticationEntryPoint digestAuthenticationEntryPoint) throws Exception {
        DigestAuthenticationFilter digestAuthenticationFilter = new DigestAuthenticationFilter();
        digestAuthenticationFilter.setAuthenticationEntryPoint(digestEntryPoint());
        digestAuthenticationFilter.setUserDetailsService(userDetailsServiceBean());
        return digestAuthenticationFilter;
    }

    @Bean
    public DigestAuthenticationEntryPoint digestEntryPoint() {
        DigestAuthenticationEntryPoint digestAuthenticationEntryPoint = new DigestAuthenticationEntryPoint();
        digestAuthenticationEntryPoint.setKey(key);
        digestAuthenticationEntryPoint.setRealmName(realm);
        digestAuthenticationEntryPoint.setNonceValiditySeconds(nonceValiditySeconds);
        return digestAuthenticationEntryPoint;
    }*/

    /*@Bean
    public SessionRegistry sessionRegistry() {
        SessionRegistry sessionRegistry = new SessionRegistryImpl();
        return sessionRegistry;
    }

    // Register HttpSessionEventPublisher
    @Bean
    public static ServletListenerRegistrationBean httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean(new HttpSessionEventPublisher());
    }*/

    /* Form-Based Authentication with Remember me (Simple hash-based token approach) *//*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            *//* Anonymous Authentication *//*
            .anonymous()
//                .authorities("ANONYMOUS_USER_ROLE")
                .and()
            .authorizeRequests()
                .antMatchers("/", "/home", "/logout").permitAll()
//                .antMatchers("/").hasAnyRole("ROLE_ANONYMOUS")
                .anyRequest().authenticated()
                .and()
            .formLogin()
//                .failureUrl("")
//                .loginPage("/login")
                .permitAll()
                .and()
                .rememberMe()
                .key("challenge")
                .rememberMeCookieName("remember-me-challenge")
                .rememberMeParameter("remember-me-challenge")
                .tokenValiditySeconds(86400) // one day
                .and()
            .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
            .sessionManagement()
                .invalidSessionUrl("/")
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredUrl("/login?expired")
//                .and().csrf()
//                .and().exceptionHandling().accessDeniedPage("/Access_Denied")
        ;
    }*/
}
