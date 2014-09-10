package edu.csu.speedo.download;

import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import edu.csu.speedo.ui.MainWindow;

public class ReadAssignFile {

	public static void readAssignFile() throws IOException
	{
		File speedoFile = new File("config/speedoFile.spd");
		String src ="";
		String dest="";
		String finish="";
		String inf="";
		if(speedoFile.exists())	
		{//如果配置文件存在,读配置文件
			FileReader fr = new FileReader(speedoFile);
			BufferedReader bw =new BufferedReader(fr);
//			MainWindow.imageName=bw.readLine();
			
			while( (inf=bw.readLine()) != null)
			{
				//提取信息
				src = inf.substring(inf.indexOf("src:")+4, inf.indexOf("dest:")-1);
				dest=inf.substring(inf.indexOf("dest:")+5, inf.indexOf("finish:")-1);
				finish =inf.substring(inf.indexOf("finish")+7);
				System.out.println(src);
				System.out.println(dest);
				System.out.println(inf);
				String name =src.substring(src.lastIndexOf('/') + 1);
				if(finish.equals("false"))
				{
					new 	Download().down(src, dest, name);
				}
			}
			fr.close();
			bw.close();
		}
//		else
//			MainWindow.imageName = "img/background5.jpg";
	}
}
