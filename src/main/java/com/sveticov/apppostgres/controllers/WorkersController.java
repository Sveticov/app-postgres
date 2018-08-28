package com.sveticov.apppostgres.controllers;

import com.sveticov.apppostgres.models.Workers;
import com.sveticov.apppostgres.service.WorkersService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkersController {

    @Autowired
    WorkersService workersService;


    @GetMapping("/s/w")
    public void save(){
//        Workers workers=new Workers("Gena","Kredenec","gena@ukr.com");
//        workersService.save(workers);
    }

    @GetMapping("/a/w")
    public String allVisi(){
        String result="";
        for (Workers worker:workersService.findAll()) {
            result+=worker.toString()+"<br>";
        }
        return result;
    }
}
