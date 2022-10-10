package com.wizeline.maven.learningjavamaven.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;

import com.wizeline.maven.learningjavamaven.model.Post;

@FeignClient(value = "getAccountsClient", url = "https://jsonplaceholder.typicode.com/")
public interface AccountsJSONClient {
    @GetMapping(value = "post/{postId}", produces = "application/json")
    abstract Post getPostById(@PathVariable("postId") Long postId);
}
