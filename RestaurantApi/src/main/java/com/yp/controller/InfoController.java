package com.yp.controller;

import com.yp.dto.Info;
import com.yp.service.InfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Info Controller")
@CrossOrigin("*")
@RestController
@RequestMapping("/infos")
public class InfoController {
    @Autowired
    InfoService infoService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') || hasAuthority('USER')")
    public Info lstInfo(){
        return infoService.getInfo();
    }

}
