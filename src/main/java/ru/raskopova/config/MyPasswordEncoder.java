package ru.raskopova.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.raskopova.exceptions.ErrorMessage;
import ru.raskopova.exceptions.HashAlgorithmError;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

@Component
public class MyPasswordEncoder implements PasswordEncoder {
    @Value("${config.security.secret}")
    private String secret;

    private static final PasswordEncoder INSTANCE = new MyPasswordEncoder();

    public MyPasswordEncoder() {
    }

    @SneakyThrows
    @Override
    public String encode(CharSequence rawPassword) {
        return getMd5Hash(rawPassword.toString() + secret);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String rawPasswordHashMD5 = (rawPassword.toString().toLowerCase(Locale.ROOT) + secret);
        return rawPasswordHashMD5.equals(encodedPassword);
    }

    public static PasswordEncoder getInstance() {
        return INSTANCE;
    }


    @SneakyThrows
    public String getMd5Hash(String source) {
        try {
            var md = MessageDigest.getInstance("SHA-1");
            md.update(source.getBytes());
            byte[] digest = md.digest();
            return bytesToHex(digest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            ErrorMessage errorMessage = new ErrorMessage(-3,
                    "Не удалось вычислить hash значения(неподдерживаемый алгоритм)");
            throw new HashAlgorithmError(errorMessage);
        }
    }

    private String bytesToHex(byte[] bytes) {
        var builder = new StringBuilder();
        for (var b : bytes) {
            builder.append(String.format("%02x", b & 0xff));
        }
        return builder.toString();
    }

}