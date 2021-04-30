package ru.raskopova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.raskopova.model.dto.UserDTO;
import ru.raskopova.model.entity.Role;
import ru.raskopova.model.entity.User;
import ru.raskopova.repository.RoleRepository;
import ru.raskopova.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final ConversionService conversionService;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final int USER_ROLE_ID = 1; // id роли юзера в бд


    public UserServiceImpl(@Qualifier("mvcConversionService") ConversionService conversionService, @Autowired UserRepository userRepository, RoleRepository roleRepository) {
        this.conversionService = conversionService;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void addUser(String username, String password) {
        UserDTO userDTO = new UserDTO().setUsername(username).setPassword(password).setRole(new Role().setId(USER_ROLE_ID));
        userRepository.save(Objects.requireNonNull(conversionService.convert(userDTO, User.class)));

    }

    @Override
    public Optional<User> getByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser;
    }

    @Override
    public boolean validateCredentials(String username, String password) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(username, password);
        return userOptional.filter(user -> (password.equals(user.getPassword()))).isPresent();
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
