package ru.raskopova.exceptions;

public class RoleErrors extends Exception {

    private final ErrorMessage errorMessage;

    public RoleErrors(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorMessage getErrorMassage() {
        return errorMessage;
    }

}
