package com.athloneitf.ui.media;
import java.io.*;
import sun.audio.*;

public class Audio {
	public static void playWelcome() 
			  throws Exception
			  {
			    // open the sound file as a Java input stream
			    String gongFile = "audio/welcome.wav";
			    InputStream in = new FileInputStream(gongFile);

			    // create an audiostream from the inputstream
			    AudioStream audioStream = new AudioStream(in);

			    // play the audio clip with the audioplayer class
			    AudioPlayer.player.start(audioStream);
			  }
	
	public static void playGoodbye() 
			  throws Exception
			  {
			    // open the sound file as a Java input stream
			    String gongFile = "audio/goodbye.wav";
			    InputStream in = new FileInputStream(gongFile);

			    // create an audiostream from the inputstream
			    AudioStream audioStream = new AudioStream(in);

			    // play the audio clip with the audioplayer class
			    AudioPlayer.player.start(audioStream);
			  }
}
