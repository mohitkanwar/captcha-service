package com.mk.captchaservice;

import jakarta.servlet.http.HttpServletResponse;
import net.logicsquad.nanocaptcha.audio.AudioCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CaptchaController {

    @Autowired
    private CaptchaUtil captchaUtil;
    @Autowired
    private CaptchaAudioGenerator captchaAudioGenerator;

    @GetMapping("/captcha/{text}")
    public Map<String, String> getCaptcha(@PathVariable String text) throws IOException {
        //String captchaText = captchaUtil.generateCaptchaText();
        String captchaText = text;
        System.out.println(captchaText);
        String captchaImage = captchaUtil.generateCaptchaImage(captchaText);

        // Store the captchaText in session or database for later verification
        // For simplicity, we'll use a static map here
        CaptchaStorage.storeCaptcha(captchaText);

        Map<String, String> response = new HashMap<>();
        response.put("captchaImage", captchaImage);
        return response;
    }

}
