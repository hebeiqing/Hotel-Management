package com.HM.Hotel.Management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DataController {

    @RequestMapping("/dateist")
    public  String date(){

        return "admin/index";

    }
    @RequestMapping("/introduce")
    public  String introduce(){

        return "admin/introduce";

    }
}
