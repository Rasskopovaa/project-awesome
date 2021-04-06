package ru.raskopova.service;

import ru.raskopova.model.entity.User;

public interface UserService {
    /**
     * добавляет юсера в бд
     *
     * @param username
     * @param password
     */
    void addUser(String username, String password);

    /**
     * возвращает юзера по имени
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * проверяет есть ли такой юзер в бд при авторизации
     *
     * @param username
     * @param password
     * @return
     */
    public boolean checkAuth(String username, String password);
}
