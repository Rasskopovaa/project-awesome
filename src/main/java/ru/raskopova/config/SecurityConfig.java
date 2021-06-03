package ru.raskopova.config;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;
import ru.raskopova.model.entity.Roles;
import ru.raskopova.model.entity.User;
import ru.raskopova.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Pattern;

@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomAuthenticationProvider customAuthenticationProvider;
    private final UserService userService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .requireCsrfProtectionMatcher(new RequestMatcher() {
                    private final Pattern allowedMethods = Pattern.compile("^(GET|POST|PUT|DELETE)$");

                    /**
                     * Allow REST methods
                     *
                     * @param request HttpServletRequest
                     * @return false if permit and true if deny
                     */
                    @Override
                    public boolean matches(HttpServletRequest request) {
                        return !allowedMethods.matcher(request.getMethod()).matches();
                    }
                })
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1.0/user/**").hasAnyRole(Roles.USER.toString(), Roles.ADMIN.name())
                .antMatchers("/admin/**").hasRole(Roles.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/books/**").permitAll()
                .antMatchers(HttpMethod.POST, "/books/**").hasRole(Roles.ADMIN.name())
                .antMatchers("/**").permitAll()
                .antMatchers("/register").permitAll()
                .and().formLogin()
                .successHandler(new AuthenticationSuccessHandler() {

                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                                        Authentication authentication) throws IOException, ServletException {
                        Optional<User> user = userService.findByUsername(authentication.getName());
                        if (user.isPresent()) {
                            if (user.get().getUserRole().getRoleName().equals("ROLE_ADMIN")) {
                                response.sendRedirect("/admin/books");
                            } else if (user.get().getUserRole().getRoleName().equals("ROLE_USER")) {
                                response.sendRedirect("/books");
                            }
                        }
                    }
                })
                .and().logout().logoutSuccessUrl("/")
        ;
    }

}
