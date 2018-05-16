package com.phantom.pickme.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @GetMapping("/")
    public String hello() {
        return "<h1>hello! your all of CodeDeploy finished!</h1><br><p>next step is the 'NginX!</p>";
    }
}
