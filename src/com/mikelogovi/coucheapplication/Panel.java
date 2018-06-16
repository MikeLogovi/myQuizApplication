package com.mikelogovi.coucheapplication;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
public class Panel extends JPanel{
         BufferedImage bf=null;
	     public Panel() {
        	 try {
					bf= ImageIO.read(new File("src/com/mikelogovi/images/quiz.png")); 
				  }catch(IOException e) {
						e.printStackTrace();
				  }       
         
         }
         public void paintComponent(Graphics graphic) {
        	  super.paintComponent(graphic);
        	  graphic.drawImage(bf, 0,0, null);
         }
}
