package ru.raskopova.service;

import ru.raskopova.model.dto.UserDTO;
import ru.raskopova.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    /**
     * добавляет юзера в бд
     *
     * @param username
     * @param password
     */
    void createUser(String username, String password);

    /**
     * возвращает юзера по имени
     *
     * @param username
     * @return
     */
    Optional<User> findByUsername(String username);

    /**
     * проверяет есть ли такой юзер в бд при авторизации,проверяет введённый пароль
     *
     * @param username
     * @param password
     * @return
     */
    public boolean validateCredentials(String username, String password);

    /**
     * возвращает список юзеров
     *
     * @return
     */
    List<UserDTO> getAllUsers();

    /**
     * обновляет роль юзера
     *
     * @param username
     * @param role
     * @return
     */
    User updateUserRole(String username, String role);
}
