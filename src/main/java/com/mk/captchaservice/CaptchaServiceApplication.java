package com.mk.captchaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CaptchaServiceApplication {

    public static void main(String[] args) {
        System.setProperty("freetts.voices",
                "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        SpringApplication.run(CaptchaServiceApplication.class, args);
    }

}
