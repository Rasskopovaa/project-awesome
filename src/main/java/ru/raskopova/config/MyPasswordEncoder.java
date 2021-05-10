package ru.raskopova.config;


import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
public class MyPasswordEncoder implements PasswordEncoder {
    @Value("${config.security.secret}")
    private String secret;

    public MyPasswordEncoder() {
    }

    @SneakyThrows
    @Override
    public String encode(CharSequence rawPassword) {
        return getMd5Hash(rawPassword.toString() + secret);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String rawPasswordHashMD5 = (rawPassword.toString() + secret);
        return rawPasswordHashMD5.equals(encodedPassword);
    }


    @SneakyThrows
    public String getMd5Hash(String source) {
        var md = MessageDigest.getInstance("SHA-1");
        md.update(source.getBytes());
        byte[] digest = md.digest();
        return bytesToHex(digest).toUpperCase();
    }

    private String bytesToHex(byte[] bytes) {
        var builder = new StringBuilder();
        for (var b : bytes) {
            builder.append(String.format("%02x", b & 0xff));
        }
        return builder.toString();
    }

}