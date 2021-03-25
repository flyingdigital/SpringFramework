package com.flyingdigital.factory;

import com.flyingdigital.user.User;

public class StaticFactory {
    public static User createUser(){
        return new User();
    }
}
