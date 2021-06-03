package ru.raskopova.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class HashAlgorithmError extends Exception {
    private ErrorMessage errorMessage;

}