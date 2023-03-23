/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.wizeline.maven.learningjava.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;

/**
 * ItemReader for UserJob.
 */
public class UserReader implements ItemReader<String> {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(UserReader.class);

    private String[] stringArray = {"Phoebe Buffay", "Rachel Green", "Monica Geller", "Chandler Bing", "Ross Geller", "Joey Tribbiani"};

    private int index = 0;

    @Override
    public String read() throws Exception {
        if (index >= stringArray.length) {
            return null;
        }
        String data = index + " " + stringArray[index];
        index++;
        LOGGER.info("UserReader: Reading data: {}", data);
        return data;
    }
}
