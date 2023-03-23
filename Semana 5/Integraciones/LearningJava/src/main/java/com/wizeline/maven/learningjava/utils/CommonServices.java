/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.wizeline.maven.learningjava.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wizeline.maven.learningjava.model.ResponseDTO;
import com.wizeline.maven.learningjava.service.UserService;

/**
 * Class Description goes here.
 * Created by enrique.gutierrez on 26/09/22
 */

@Component
public class CommonServices {

    @Autowired
    UserService userService;

    public ResponseDTO login(String user, String password) {
        //UserService UserService = new UserServiceImpl();
        return userService.login(user, password);
    }

}
