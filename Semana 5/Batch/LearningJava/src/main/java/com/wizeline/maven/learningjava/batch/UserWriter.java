/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.wizeline.maven.learningjava.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

/**
 * ItemWriter for UserJob.
 */
public class UserWriter implements ItemWriter<String> {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(UserWriter.class);

    @Override
    public void write(List<? extends String> list) throws Exception {
        for (String data: list) {
            LOGGER.info("UserWriter: Writing data: " + data);
        }
        LOGGER.info("UserWriter: Writing data completed!!");
    }
}
