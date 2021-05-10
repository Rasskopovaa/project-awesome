package ru.raskopova.config;

import lombok.SneakyThrows;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.raskopova.repository.RoleRepository;
import ru.raskopova.repository.UserRepository;

import java.util.NoSuchElementException;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserAuthenticationProvider(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @SneakyThrows
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String password = passwordEncoder.encode(authentication.getCredentials().toString());
        String login = authentication.getName();
        if (userRepository.findByUsername(login).isEmpty()) {
            throw new BadCredentialsException(("Пользователь с указаным именем не найден"));
        } else {
            ru.raskopova.model.entity.User user = userRepository.findByUsername(login).orElseThrow(NoSuchElementException::new);
            String role = String.valueOf((roleRepository.findById(user.getUserRole().getId())));
            if (!password.equals(user.getPassword())) {
                throw new BadCredentialsException(("Неверный пароль"));
            }
            UserDetails principal = User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(role)
                    .build();
            return new UsernamePasswordAuthenticationToken(principal, password, principal.getAuthorities());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
