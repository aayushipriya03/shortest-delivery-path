package com.deliverysystem.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@AllArgsConstructor
@Slf4j
public class Server {

    private  int resources;
    private String name;


    public void serverCall(){
        log.info("hello from "+name);
    }
}
