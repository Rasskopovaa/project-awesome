package ru.raskopova.service;

import ru.raskopova.model.entity.Role;

import java.util.Set;

public interface RoleService {
    /**
     * возвращает лист ролей
     */
    Set<Role> getAllRoles();
}
