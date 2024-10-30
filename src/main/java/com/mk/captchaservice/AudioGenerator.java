package com.mk.captchaservice;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class AudioGenerator {

    private static final String VOICE_NAME = "kevin16"; // or any other available voice
    private static final String OUTPUT_DIRECTORY = "audio_files/";

    public static void main(String[] args) {
        System.setProperty("freetts.voices",
                "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory" );
        try {
            generateAudioFiles();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generateAudioFiles() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice[] voices = voiceManager.getVoices();
        File dir = new File(OUTPUT_DIRECTORY);
        if (!dir.exists()) {
            dir.mkdir();
        }
        for (Voice voice : voices) {

            voice.allocate();

            // Create output directory if it doesn't exist


            // Generate audio files for A-Z
            for (char ch = 'A'; ch <= 'Z'; ch++) {
                generateAudioForCharacter(voice, ch);
            }

            // Generate audio files for 0-9
            for (char ch = '0'; ch <= '9'; ch++) {
                generateAudioForCharacter(voice, ch);
            }
            voice.deallocate();
        }


    }

    private static void generateAudioForCharacter(Voice voice, char character) throws IOException {
        // Create a temporary file for audio output
        File audioFile = new File(OUTPUT_DIRECTORY + character + "_" + voice.getName() + ".wav");

        // Capture audio to a ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Use a custom AudioInputStream to capture audio output
        AudioFormat audioFormat = new AudioFormat(44100.0f, 16, 1, true, false);
        AudioInputStream audioInputStream = new AudioInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), audioFormat, AudioSystem.NOT_SPECIFIED);

        // Speak the character
        voice.speak(String.valueOf(character));

        // Write the audio data to a file
        AudioSystem.write(audioInputStream, AudioFileFormat.Type.WAVE, audioFile);
    }

}
