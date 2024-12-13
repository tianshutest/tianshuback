package com.example.tianshu.client;

import org.springframework.web.bind.annotation.GetMapping;

public interface InterfaceClient {

    @GetMapping("/updateCount")
    boolean updateCount();
}
