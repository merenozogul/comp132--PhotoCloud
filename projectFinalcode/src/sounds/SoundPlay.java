package sounds;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlay {

	public SoundPlay() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Takes a relative path of a sounds and play it
	 * @param relative path of the sound
	 */
	public static void playSound(String soundName) {
	    File sound = new File(soundName);
	    Clip clip = null;

	    try {
	        AudioInputStream audio = AudioSystem.getAudioInputStream(sound);
	        clip = AudioSystem.getClip();
	        clip.open(audio);
	    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
	        e.printStackTrace();
	    }

	    clip.start();
	}
	
	
	/**
	 * Plays opening sound
	 */
	public static void soundOpening() {
		playSound("./src/sounds/opening_sound.wav");
	}
	
	/**
	 * Plays liking or disliking sound
	 */
	public static void likedDislikedSound() {
		playSound("./src/sounds/like_dislike.wav");
	}
	
	/**
	 * Plays posting sound
	 */
	public static void postSound() {
		playSound("./src/sounds/post.wav");
	}
	
	/**
	 * Plays logged in sound
	 */
	public static void loggedInSound() {
		playSound("./src/sounds/loggedin.wav");
	}
	
	/**
	 * Plays comment added sound
	 */
	public static void commetSound() {
		playSound("./src/sounds/comment.wav");
	}
	
	/**
	 * Plays photo deleted sound
	 */
	public static void deleteSound() {
		playSound("./src/sounds/delete.wav");
	}
	/**
	 * Plays logout sound
	 */
	public static void logoutSound() {
		playSound("./src/sounds/logout.wav");
	}
	
	/**
	 * Plays send message sound
	 */
	public static void sendMessageSound() {
		playSound("./src/sounds/sendMessage.wav");
	}
}
