/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.wizeline.maven.learningjava.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

/**
 * Processor for UserJob.
 */
public class UserProcessor implements ItemProcessor<String, String> {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(UserProcessor.class);

    @Override
    public String process(String data) throws Exception {
        LOGGER.info("UserProcessor: Processing data: {}", data);
        data = data.toUpperCase();
        return data;
    }
}
