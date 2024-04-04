package com.yoonje.authenticationexample.common.util;

import org.springframework.security.core.userdetails.User;

import java.util.UUID;

public class UuidConverter {

    public static UUID fromUser(User user){
        return UUID.fromString(user.getUsername());
    }

}
