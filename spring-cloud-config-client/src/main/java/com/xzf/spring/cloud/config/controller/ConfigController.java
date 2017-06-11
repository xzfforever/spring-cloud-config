package com.xzf.spring.cloud.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/config")
public class ConfigController {

    @Value("${user.name}")
    private String userName;

    @GetMapping(value="/data")
    public String getUserName(){
        return this.userName;
    }


}