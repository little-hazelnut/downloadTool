package edu.csu.speedo.download;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.media.*;

import javax.media.MediaLocator;

public class VoicePlay  {
		void playvoice()
		{
			String audioName = new String("sound\\1.mp3");
			try {
				
				MediaLocator ml = new MediaLocator("file:"+audioName);
				Player p = Manager.createRealizedPlayer(ml);
				p.start();
				Thread.currentThread().sleep(5000);
				p.close();
				p.stop();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoPlayerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CannotRealizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public static void main(String[] args) {
			VoicePlay vp =new VoicePlay();
			vp.playvoice();
		}
		
}
