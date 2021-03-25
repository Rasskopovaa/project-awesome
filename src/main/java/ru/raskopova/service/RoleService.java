package ru.raskopova.service;

import ru.raskopova.model.entity.Role;

public interface RoleService {

    /**
     * добавляет роль в бд
     *
     * @param role
     * @return
     */
    Role addRole(Role role);
}
