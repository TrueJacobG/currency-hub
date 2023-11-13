package com.truejacobg.currencyhub.security;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.Base64;

public class Encoder {

    private final String salt = "$2a$10$EUoCY87J.YJD6F4foMJGouI.NrQ1l2hvOoKhdNvi/wwlgtY7433N.";

    public String endoce(String password){
        return BCrypt.hashpw(password,salt);
    }
}
