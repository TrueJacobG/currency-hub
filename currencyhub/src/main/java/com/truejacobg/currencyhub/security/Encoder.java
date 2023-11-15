package com.truejacobg.currencyhub.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Configuration
public class Encoder {
    @Value("${encoder.salt}")
    private String salt;

    public String encode(String password){
        return BCrypt.hashpw(password,salt);
    }
}
