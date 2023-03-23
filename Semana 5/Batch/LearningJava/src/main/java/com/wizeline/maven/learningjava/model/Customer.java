/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.wizeline.maven.learningjava.model;

import java.io.Serializable;
import java.util.Calendar;

import lombok.Data;

/**
 * Class Description goes here.
 * Created by jose.vazquez on 01/11/22
 */
@Data
public class Customer implements Serializable {

    private int id;
    private String name;
    private Calendar birthday;
    private int transactions;

    @Override
    public String toString() {
        return String.format(
                "#%s, %s born on %3$tb %3$te, %3$tY, finished %4$s transactions",
                id,
                name,
                birthday.getTime(),
                transactions
        );
    }

}
