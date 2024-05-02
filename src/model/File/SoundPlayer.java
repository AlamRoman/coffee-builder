package model.File;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundPlayer implements Runnable{
	
	private Clip clip;
	private AudioInputStream audioInputStream;
	
	private Thread t;
	
	public SoundPlayer() {
		t = new Thread(this);
	}

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

