package com.ninetosix.adminmodule.nts.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebErrorController implements ErrorController {

    @GetMapping("/error")
    public String redirectRoot() {
        return "index.html";
    }
}