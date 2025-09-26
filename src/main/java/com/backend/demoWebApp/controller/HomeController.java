package com.backend.demoWebApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {


//    @ResponseBody
    @RequestMapping("/")
    public String greet(){
        return "Hello World";
    }

    @RequestMapping("/about")
    public String about(){
        return "about the page";
    }

}
