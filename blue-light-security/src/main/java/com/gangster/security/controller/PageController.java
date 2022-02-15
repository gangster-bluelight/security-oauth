package com.gangster.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author blue-light
 * @date 2022-02-14
 * @description
 */
@Controller
public class PageController {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
