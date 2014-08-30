package com.athloneitf.ui.media;
import java.io.*;
import sun.audio.*;

public class Audio {
	
	private static final String scanInFile="chime_up.wav";
	private static final String scanOutFile="chime_down.wav";
	private static boolean audio=true;
	
	public static boolean isAudio() {
		return audio;
	}

	public static void setAudio(boolean a) {
		audio = a;
	}

	public static void playWelcome() 
			  throws Exception
			  {
			    // open the sound file as a Java input stream
			   // InputStream in = new FileInputStream("audio/"+scanInFile);

			    InputStream in = Audio.class.getClassLoader().getResourceAsStream("audio/"+scanInFile);
			    
			    // create an audiostream from the inputstream
			    AudioStream audioStream = new AudioStream(in);

			    // play the audio clip with the audioplayer class
			    if(isAudio())AudioPlayer.player.start(audioStream);
			  }
	
	public static void playGoodbye() 
			  throws Exception
			  {
			    // open the sound file as a Java input stream
			    //InputStream in = new FileInputStream("audio/"+scanOutFile);

			    InputStream in = Audio.class.getClassLoader().getResourceAsStream("audio/"+scanOutFile);
			    // create an audiostream from the inputstream
			    AudioStream audioStream = new AudioStream(in);

			    // play the audio clip with the audioplayer class
			    if(isAudio())AudioPlayer.player.start(audioStream);
			  }
}
