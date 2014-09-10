package edu.csu.speedo.download;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import edu.csu.speedo.model.ModelUser;
import edu.csu.speedo.ui.MainWindow;

public class WriteAssignFile {
	//写配置文件方法
	public static void writeFile() throws IOException
	{
			
		File speedoFile = new File("config/speedoFile.spd");
		if(!speedoFile.exists())		//如果配置文件不存在，新建
			speedoFile.createNewFile();
		else								//如果配置文件存在，删除再新建
		{
			speedoFile.delete();
			speedoFile.createNewFile();
		}
		//文件写入流
		FileWriter fw = new FileWriter(speedoFile);
		BufferedWriter bw =new BufferedWriter(fw);
		
		String src ="";
		String dest="";
		String finish="";
		
//		bw.write(MainWindow.imageName);
//		bw.newLine();
		
		for(int i=0;i<ModelUser.allDownloadDateInf.size();i++)
		{
			src =(String) ModelUser.allDownloadDateInf.get(i).get(0);
			dest =(String) ModelUser.allDownloadDateInf.get(i).get(1);
			finish =(String) ModelUser.allDownloadDateInf.get(i).get(4);
			
			bw.write("src:"+src+" "+"dest:" +dest+" "+"finish:"+finish);
			bw.newLine();
		}
		bw.close();
		fw.close();	
		
	}
}
