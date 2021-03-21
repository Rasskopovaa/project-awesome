package ru.raskopova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import ru.raskopova.model.dto.UserDTO;
import ru.raskopova.model.entity.User;
import ru.raskopova.repository.UserRepository;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {


    private final ConversionService conversionService;

    private final UserRepository userRepository;


    public UserServiceImpl(@Qualifier("mvcConversionService") ConversionService conversionService, @Autowired UserRepository userRepository) {
        this.conversionService = conversionService;
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(UserDTO userDTO) {
        return userRepository.save(conversionService.convert(userDTO, User.class));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

}
