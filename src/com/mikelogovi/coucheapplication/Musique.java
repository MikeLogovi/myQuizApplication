package com.mikelogovi.coucheapplication;
import java.io.*;
import javax.sound.sampled.*;
public class Musique extends Thread{
     AudioInputStream audioInputStream;
     SourceDataLine line;
     File fichier = new File("src/com/mikelogovi/musiques/Heros.wav");
		public void run(){
		try {
			AudioFileFormat format = AudioSystem.getAudioFileFormat(fichier);
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			audioInputStream = AudioSystem.getAudioInputStream(fichier);
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AudioFormat audioFormat = audioInputStream.getFormat();
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
	 
		try {
			line=(SourceDataLine)AudioSystem.getLine(info);
			line.open(audioFormat);
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    line.start();
	    byte bytes[]=new byte[1024];
	    int bytesRead=0;
	    try {
			while((bytesRead=audioInputStream.read(bytes,0,bytes.length))!=-1) {
				  line.write(bytes, 0, bytesRead);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
		

}