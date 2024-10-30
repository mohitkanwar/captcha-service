package com.mk.captchaservice;

import com.github.cage.Cage;
import com.github.cage.GCage;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Component
public class CaptchaUtil {

    private final Cage cage = new GCage();

    public String generateCaptchaText() {
        return cage.getTokenGenerator().next();
    }

    public String generateCaptchaImage(String captchaText) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        cage.draw(captchaText, outputStream);
        return Base64.getEncoder().encodeToString(outputStream.toByteArray());
    }
}
