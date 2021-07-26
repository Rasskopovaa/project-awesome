package ru.raskopova.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ErrorMessage {
    private int errorCode;
    private String errorMessage;
}