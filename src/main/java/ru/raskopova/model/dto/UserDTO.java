package ru.raskopova.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO {
    private String username;
    private String password;
    private int roleId;

    transient private String passwordConfirm;

    @Transient
    public String getPasswordConfirm() {
        return passwordConfirm;
    }
}
