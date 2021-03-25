package ru.raskopova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.raskopova.model.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findById(int id);

    Role findByRoleName(String roleName);
}
