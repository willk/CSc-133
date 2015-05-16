package a4;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Random;

/**
 * Created by William Kinderman on 4/26/15, 9:54 PM.
 */
public class Audio {
    private AudioClip audio;
    private String[] name;
    private Random r;

    public Audio(String[] name) {
        r = new Random(System.nanoTime());
        this.name = name;
        init();
    }

    private void init() {
        try {
            String path = "." + File.separator + "audio" + File.separator;
            File f = new File(path + name[r.nextInt(name.length)]);
            if (f.exists())
                audio = Applet.newAudioClip(f.toURI().toURL());
            else
                throw new RuntimeException("Sound file not found: " + name);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Sound malformed URI: " + e);
        }
    }
    public void play() {
        init();
        audio.play();
    }

    public void loop() {
        audio.loop();
    }

    public void stop() {
        audio.stop();
    }


}
