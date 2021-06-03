package ru.raskopova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.raskopova.model.dto.UserDTO;
import ru.raskopova.model.entity.Role;
import ru.raskopova.model.entity.Roles;
import ru.raskopova.model.entity.User;
import ru.raskopova.repository.RoleRepository;
import ru.raskopova.repository.UserRepository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final ConversionService conversionService;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;


    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(@Qualifier("mvcConversionService") ConversionService conversionService, @Autowired UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.conversionService = conversionService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(String username, String password) {
        UserDTO userDTO = new UserDTO()
                .setUsername(username)
                .setPassword(password)
                .setRole(new Role().setId(Roles.USER.getId()));
        var userEntity = conversionService.convert(userDTO, User.class);
        assert userEntity != null;
        userEntity.setPassword(passwordEncoder.encode(password).toLowerCase(Locale.ROOT));
        userRepository.save(userEntity);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean validateCredentials(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(username, passwordEncoder.encode(password).toLowerCase(Locale.ROOT));
        return userOptional.isPresent();
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> conversionService.convert(user, UserDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public User updateUserRole(String username, String role) {
        User user = userRepository.findByUsername(username).orElseThrow(IllegalArgumentException::new);
        user.setUserRole(roleRepository.findRoleByRoleName(role));
        return userRepository.save(user);
    }
}
