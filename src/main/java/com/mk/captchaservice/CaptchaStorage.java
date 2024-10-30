package com.mk.captchaservice;

import java.util.HashMap;
import java.util.Map;

public class CaptchaStorage {
    private static final Map<String, String> captchaStore = new HashMap<>();

    public static void storeCaptcha(String captchaText) {
        captchaStore.put("captcha", captchaText);
    }

    public static String getCaptcha() {
        return captchaStore.get("captcha");
    }
}
