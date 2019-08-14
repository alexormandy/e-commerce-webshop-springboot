package com.webshop.webshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String getIndexPage (HttpSession session) {

        return "index";
    }

}