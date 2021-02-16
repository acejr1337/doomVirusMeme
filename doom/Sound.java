package doom;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	
	private static Clip clip;
	private static AudioInputStream inputStream;
	
	public static synchronized void playSound(final String url) {
		new Thread(new Runnable() {
			// The wrapper thread is unnecessary, unless it blocks on the
			// Clip finishing; see comments.
			public void run() {
				try {
					 clip = AudioSystem.getClip();
					 inputStream = AudioSystem.getAudioInputStream(
							Sound.class.getResource("/" + url));
					clip.open(inputStream);
					clip.start(); 
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}).start();
	}
	public static synchronized void stopSound(String url) {
		clip.stop();
	}
}
