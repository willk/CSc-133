package a3;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;

/**
 * Created by William Kinderman on 4/26/15, 9:54 PM.
 */
public class Audio {
    private AudioClip audio;

    public Audio(String name) {
        try {
            File f = new File(name);
            if (f.exists())
                audio = Applet.newAudioClip(f.toURI().toURL());
            else
                throw new RuntimeException("Sound file not found: " + name);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Sound malformed URI: " + e);
        }
    }

    public void play() {
        audio.play();
    }

    public void loop() {
        audio.loop();
    }

    public void stop() {
        audio.stop();
    }


}
