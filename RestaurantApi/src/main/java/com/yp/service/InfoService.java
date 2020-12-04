package com.yp.service;

import com.yp.dto.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoService {
    @Autowired
    private Info info;

    public Info getInfo(){
        return info;
    }


}
