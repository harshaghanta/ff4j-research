package com.sharshag.ff4jresearch.services;

import org.ff4j.FF4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.ui.context.Theme;

import com.sharshag.ff4jresearch.config.FF4jConfig;

@Service
public class FF4JService {

    @Autowired
    private FF4j ff4j;

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            while (true) {
                
                if(ff4j.check(FF4jConfig.HELLO_FEATURE)) {
                    System.out.println("Feature enabled");                   
                }
                else {
                    System.out.println("Feature not enabled yet");
                }
                Thread.sleep(5000);
            }
        };
    }
    
}
