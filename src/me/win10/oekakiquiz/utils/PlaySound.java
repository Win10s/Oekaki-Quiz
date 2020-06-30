package me.win10.oekakiquiz.utils;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class PlaySound {

	private Clip clip;
	private File soundFile;

	public PlaySound(String file) {
		setFile(file);
	}
	public PlaySound(File file) {
		setFile(file);
	}

	public void setFile(String file){
		setFile(new File(file));
	}
	public void setFile(File soundFile){
		try{
			this.soundFile = soundFile;
			AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
			clip = AudioSystem.getClip();
			clip.open(sound);
		}
		catch(Exception e){}
	}

	public File getFile() {
		return this.soundFile;
	}

	public void setVolume(float volume) {
	    if (volume < 0f || volume > 1f)
	        throw new IllegalArgumentException("Volume not valid: " + volume);
	    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	    gainControl.setValue(20f * (float) Math.log10(volume));
	}

	public float getVolume() {
	    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
	    return (float) Math.pow(10f, gainControl.getValue() / 20f);
	}

	public double getSoundSeconds() {
	    AudioInputStream stream = null;
	    try {
	        stream = AudioSystem.getAudioInputStream(getFile());

	        AudioFormat format = stream.getFormat();

	        return getFile().length() / format.getSampleRate() / (format.getSampleSizeInBits() / 8.0) / format.getChannels();
	    }
	    catch (Exception e) {
	        return -1;
	    }
	    finally {
	        try { stream.close(); } catch (Exception ex) { }
	    }
	}
	public void play(){
		clip.setFramePosition(0);
		clip.start();
		Timer nowTimer = new Timer();
	    nowTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				clip.stop();
				cancel();
			}

	    }, (long)(getSoundSeconds() * 1000L) + 1000L);
	}

}