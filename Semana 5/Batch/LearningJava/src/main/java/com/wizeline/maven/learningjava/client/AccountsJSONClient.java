/*
 * Copyright (c) 2022 Nextiva, Inc. to Present.
 * All rights reserved.
 */
package com.wizeline.maven.learningjava.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wizeline.maven.learningjava.model.Post;

/**
 * Class Description goes here.
 * Created by enrique.gutierrez on 28/09/22
 */
@FeignClient(value="getAccountsClient", url="https://jsonplaceholder.typicode.com/")
public interface AccountsJSONClient {
    @GetMapping(value = "/posts/{postId}", produces = "application/json")
    Post getPostById(@PathVariable("postId") Long postId);
}