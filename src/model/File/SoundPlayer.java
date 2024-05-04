package model.File;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * <p>
 * This class provides functionality to play sound files. It implements a thread to support
 * concurrent execution of sound playback. It includes methods to play a specific sound file and to play a default error sound.
 * </p>
 */
public class SoundPlayer implements Runnable{
	
	private Clip clip;
	private AudioInputStream audioInputStream;
	
	private Thread t;
	
	/**
     * <p>
     * Constructor for the SoundPlayer class. Initializes a new thread for concurrent sound playback.
     * </p>
     */
	public SoundPlayer() {
		t = new Thread(this);
	}

	/**
     * <p>
     * Plays the specified sound file.
     * </p>
     *
     * @param soundFilePath The path of the sound file to be played.
     */
    public void playSound(String soundFilePath) {
        try { 
            
        	audioInputStream = AudioSystem.getAudioInputStream(new File(soundFilePath).getAbsoluteFile()); 
            
        	t.start();
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public void run() {
		try {
			clip = AudioSystem.getClip(); 
            
            clip.open(audioInputStream); 
            
            clip.start();
            
            while (!clip.isRunning()) {
                Thread.sleep(10);
            }
            
            while (clip.isRunning()) {
                Thread.sleep(10);
            }
            
            clip.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * <p>
     * Plays a default error sound. Uses a predefined sound file path.
     * </p>
     */
	public void playErrorSound() {
    	try {
    		String currentPath = new java.io.File(".").getCanonicalPath();
    		String filePath = currentPath + "\\src\\resources\\ding.wav";

    		playSound(filePath);
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
}

