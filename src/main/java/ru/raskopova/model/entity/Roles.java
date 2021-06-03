package ru.raskopova.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.Optional;

@AllArgsConstructor
@Getter
public enum Roles {
    ADMIN(2, "Администратор"),
    USER(1, "Пользователь");
    private final int id;
    private final String description;

    public static Optional<Roles> getRoleById(@Nullable Integer id) {
        if (id == null) {
            return Optional.empty();
        }
        return Arrays.stream(values()).filter(relationType -> relationType.getId() == id).findFirst();
    }
}
