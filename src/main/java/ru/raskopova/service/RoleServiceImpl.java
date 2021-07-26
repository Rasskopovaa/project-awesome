package ru.raskopova.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.raskopova.model.entity.Role;
import ru.raskopova.repository.RoleRepository;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Override
    public Set<Role> getAllRoles() {
        return roleRepository.findAll().stream().collect(Collectors.toSet());
    }
}
