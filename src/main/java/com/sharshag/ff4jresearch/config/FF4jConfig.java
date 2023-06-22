package com.sharshag.ff4jresearch.config;

import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.ff4j.core.FlippingStrategy;
import org.ff4j.strategy.time.ReleaseDateFlipStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FF4jConfig {

    public static final String HELLO_FEATURE = "helloFeature";
    @Bean
    public FF4j ff4j() {
        FF4j ff4j = new FF4j();
        Feature helloFeature = new Feature(HELLO_FEATURE);
        helloFeature.setEnable(true);
        FlippingStrategy strategy = new ReleaseDateFlipStrategy("2023-06-22-08:32:00");
        helloFeature.setFlippingStrategy(strategy);
        ff4j.createFeature(helloFeature);
        return ff4j;
    }
    
}
