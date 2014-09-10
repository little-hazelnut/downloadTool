package edu.csu.speedo.download;

public class Download {

	public void down(String src,String dest,String name)
	{
		DownloadThread dt = new DownloadThread(src,dest,name);
		Thread downthread = new Thread(dt);
		downthread.start();
	}
}
