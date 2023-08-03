package com.example.parranderos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ParranderosController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}
