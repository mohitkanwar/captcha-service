package com.mk.captchaservice;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import org.springframework.stereotype.Service;
import net.logicsquad.nanocaptcha.audio.AudioCaptcha;
import org.springframework.stereotype.Service;

@Service
public class CaptchaAudioGenerator {

        public AudioCaptcha generateAudioCaptcha() {
            return AudioCaptcha.create();
        }

}
