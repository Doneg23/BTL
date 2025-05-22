//package Utils;
//
//import javax.sound.sampled.*;
//import java.io.File;
//import java.io.IOException;
//
//public class SoundPlayer {
//
//    public static void playSound(String soundFile) {
//        try {
//            File file = new File(soundFile);
//            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
//            Clip clip = AudioSystem.getClip();
//            clip.open(audioStream);
//            clip.start();
//        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
//            e.printStackTrace();
//        }
//    }
//}

package Utils;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {

    public static void playSound(String soundFile) {
        try {
            File file = new File(soundFile);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void loopSound(String soundFile) {
        try {
            File file = new File(soundFile);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Thiết lập âm thanh để lặp vô hạn
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start(); // Bắt đầu phát âm thanh lặp lại

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
