import java.net.URL;
import javax.sound.sampled.*;

public class WavSound {
    Clip clip;

    public WavSound(URL url) {
        AudioInputStream stream;
        try {
            stream = AudioSystem.getAudioInputStream(url);       
            clip = AudioSystem.getClip();
            clip.open(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //音を再生する
    public void play() {
        clip.setFramePosition(0);
        clip.start();
    }
}
