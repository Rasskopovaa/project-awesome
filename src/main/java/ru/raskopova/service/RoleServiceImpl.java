package ru.raskopova.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.raskopova.model.entity.Role;
import ru.raskopova.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }
}
