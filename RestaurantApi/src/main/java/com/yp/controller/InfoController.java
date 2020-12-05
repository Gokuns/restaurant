package com.yp.controller;

import com.yp.dto.Info;
import com.yp.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("*")
@RestController
@RequestMapping("/info")
public class InfoController {
    @Autowired
    InfoService infoService;

    @GetMapping("/list")
    public Info lstInfo(){
        return infoService.getInfo();
    }

}
