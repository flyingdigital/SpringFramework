package com.flyingdigital.factory;

import com.flyingdigital.user.User;

public class NormalFactory {

    public User createUser(){
        return new User();
    }
}
