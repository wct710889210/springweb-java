package com.wct.controller;

import com.wct.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    @Autowired
    private MyService myFactory;

    @RequestMapping("")
    public String index(){
        return "index";
    }

    @GetMapping("add")
    @ResponseBody
    public String add(int a,int b){
        return ""+myFactory.add(a,b);
    }
}
