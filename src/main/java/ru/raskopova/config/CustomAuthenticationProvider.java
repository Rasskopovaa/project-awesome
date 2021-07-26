package ru.raskopova.config;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.raskopova.exceptions.ErrorMessage;
import ru.raskopova.exceptions.RoleErrors;
import ru.raskopova.model.entity.Roles;
import ru.raskopova.repository.RoleRepository;
import ru.raskopova.repository.UserRepository;

import java.util.Locale;
import java.util.NoSuchElementException;

@Slf4j
@Component
@AllArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @SneakyThrows
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = passwordEncoder.encode(authentication.getCredentials().toString()).toLowerCase(Locale.ROOT);

        ru.raskopova.model.entity.User user = userRepository.findByUsername(login).orElseThrow(NoSuchElementException::new);
        if (user == null) {
            throw new BadCredentialsException("Неизвестный логин пользователя: " + login);
        }
        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException(("Неверный пароль!"));
        }

        String role = Roles.getRoleById(user.getUserRole().getId()).get().name();

        if (role == null) {
            ErrorMessage errorMessage = new ErrorMessage(-2, "Для пользователя: " + login + " не удалось определить роль");
            throw new RoleErrors(errorMessage);
        }

        UserDetails principal = User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(role)
                .build();

        return new UsernamePasswordAuthenticationToken(principal, password, principal.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
