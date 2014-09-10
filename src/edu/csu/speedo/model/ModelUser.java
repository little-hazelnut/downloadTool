package edu.csu.speedo.model;

import java.io.Serializable;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import edu.csu.speedo.download.DownloadThread;

public class ModelUser implements Serializable {
	
	//全部任务的Model
	public static DefaultTableModel dateModel = new DefaultTableModel(
			new Object[][] {
			}, new String[] { "状态", "文件名", "大小", "进度", "速度", "剩余时间" })
	{
		boolean[] canEdit = new boolean[] { false, false, false, false,
				false, false
		};
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return canEdit[columnIndex];
		}
	};
	
	//正在下载任务的Model
	public static DefaultTableModel dateModelDownloading = new DefaultTableModel(
			new Object[][] {
			}, new String[] { "状态", "文件名", "大小", "进度", "速度", "剩余时间" })
	{
		boolean[] canEdit = new boolean[] { false, false, false, false,
				false, false
		};
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return canEdit[columnIndex];
		}
	};
	
	//已经完成任务的Model
	public static DefaultTableModel dateModelFinish = new DefaultTableModel(
			new Object[][] {
			}, new String[] { "状态", "文件名", "大小", "进度", "速度", "剩余时间" })
	{
		boolean[] canEdit = new boolean[] { false, false, false, false,
				false, false
		};
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return canEdit[columnIndex];
		}
	};
	
	
	
	//创建一个存储全部任务行数据信息的Vector，存储包括src ，dest，finish的vector
	public static Vector<Vector> allDownloadDateInf = new Vector<Vector>();
	//创建一个存储全部任务行数据的Vecot，存储下载列表每一行信息
	public static Vector<Vector> allDownloadDate = new Vector<Vector>();
	//创建一个存储全部线程URL的vector
	public static Vector<String> urls = new Vector<String>();
	//创建一个存储下载中线程URL的vector
	public static Vector<String> urlsDownloading = new Vector<String>();
	//创建一个存储下载完成线程URL的vector
	public static Vector<String> urlsFinish = new Vector<String>();
	//创建一个存储全部任务URL对应的Runnable
	public static Vector<DownloadThread> runnables =new Vector<DownloadThread>();
	//创建一个存储正在下载任务URL对应的Runnable
	public static Vector<DownloadThread> runnablesDL =new Vector<DownloadThread>();
	//Mode初始化方法
	public static void modelDisplay() {
		for (int i = 0; i < allDownloadDate.size(); i++) {
			dateModel.addRow(allDownloadDate.get(i));
		}
	}
	
		//urls容器中添加url，往容器中添加元素，每个元素是一个url
		public static void addUrl(String urlName)
		{
			urls.add(urlName);
		}
		
		//移除urls容器中指定元素
		public static void removeUrl(int index)
		{
			urls.remove(index);
		}
		
		//获取某个线程在全部任务Table中对应url
		public static String getRowUrl(int index)
		{
			return urls.get(index);
		}
		
		//获取某个线程在全部任务Table中对应的行号
		public static int getRow(String urlId)
		{
			return urls.indexOf(urlId);
		}
	
		//urlsDownload容器中添加url，往容器中添加元素，每个元素是一个url
		public static void addUrlDL(String urlName)
		{
			urlsDownloading.add(urlName);
		}
		
		//获取某个线程在下载任务Table中对应的行号
		public static int getRowDL(String urlId)
		{
			return urlsDownloading.indexOf(urlId);
		}
		
		//获取某个线程在正在下载Table中对应url
		public static String getRowUrlDL(int index)
		{
			return urlsDownloading.get(index);
		}
		
		//移除urlsDownload容器中指定元素
		public static void removeUrlDL(int index)
		{
			urlsDownloading.remove(index);
		}
		
		
		//urlsFinish容器中添加url，往容器中添加元素，每个元素是一个url
		public static void addUrlFS(String urlName)
		{
			urlsFinish.add(urlName);
		}
		
		//获取某个线程在完成任务Table中对应的行号
		public static int getRowFS(String urlId)
		{
			return urlsFinish.indexOf(urlId);
		}
		
		//获取某个线程在完成任务Table中对应url
		public static String getRowUrlFS(int index)
		{
			return urlsFinish.get(index);
		}
		
		//移除urlsFinish容器中指定元素
		public static void removeUrlFS(int index)
		{
			urlsFinish.remove(index);
		}
			
		//Vector容器中添加vector，往容器中添加元素，每个元素是一个resoureInf Vector
		public static void addResInf(Vector vector)
		{
			allDownloadDateInf.add(vector);
		}
		
		//Vector容器中移除指定元素，每个元素是一个resoureInf Vector
		public static void removeResInf(int index)
		{
			allDownloadDateInf.remove(index);
		}
	
		//Vector容器中获取指定元素，每个元素是一个resoureInf Vector
		public static Vector getResInf(int index)
		{
			return allDownloadDateInf.get(index);
		}
			
		//容器中添加下载列表行信息方法，往容器中添加元素，每个元素是一个容器
		public static void addVector(Vector vector)
		{
			allDownloadDate.add(vector);
		}

		//容器中删除下载列表行信息方法，每个元素是一个容器
		public static void removeVector(int index)
		{
			allDownloadDate.remove(index);
		}
	
		//刷新dataModel方法,往表格中添加一列信息
		public static void addRow(Vector vector)
		{
			dateModel.addRow(vector);
		}
		
		//移除dataModel中的指定行
		public static void removeRow(int num)
		{
			dateModel.removeRow(num);
		}
	
		//设置dataModel中对应某行和列对应的单元格数值
		public static void setValue(Object obj,int row,int column)
		{
			dateModel.setValueAt(obj, row, column);
		}
		
		//刷新dataModelDownloading方法,往表格中添加一列信息
		public static void addRowDLModel(Vector vector)
		{
			dateModelDownloading.addRow(vector);
		}
		
		//移除dataModelDownloading中的指定行
		public static void removeRowDLModel(int num)
		{
			dateModelDownloading.removeRow(num);
		}
	
		//设置dataModelDownloading中对应某行和列对应的单元格数值
		public static void setValueDLModel(Object obj,int row,int column)
		{
			dateModelDownloading.setValueAt(obj, row, column);
		}
		
		//刷新dataModelFinish方法,往表格中添加一列信息
		public static void addRowFSModel(Vector vector)
		{
			dateModelFinish.addRow(vector);
		}
				
		//移除dataModelFinish中的指定行
		public static void removeRowFSModel(int num)
		{
			dateModelFinish.removeRow(num);
		}
			
		//设置dataModelFinish中对应某行和列对应的单元格数值
		public static void setValueFSModel(Object obj,int row,int column)
		{
			dateModelFinish.setValueAt(obj, row, column);
		}
		
		//全部任务runnables
		//往Runnable容器中添加对象的方法
		public static void addRunn(DownloadThread Runn)
		{
			runnables.add(Runn);
		}
		
		//往Runnable容器中获取对象的方法
		public static DownloadThread getRunn(int num)
		{
			return runnables.get(num);
		}
			
		//移除runnables容器中指定元素
		public static void removeRunnable(int index)
		{
			runnables.remove(index);
		}
		
		//正在下载runnableDL
		//往Runnable容器中添加对象的方法
		public static void addRunnDL(DownloadThread Runn)
		{
			runnablesDL.add(Runn);
		}
				
		//往Runnable容器中获取对象的方法
		public static DownloadThread getRunnDL(int num)
		{
			return runnablesDL.get(num);
		}
					
		//移除runnables容器中指定元素
		public static void removeRunnableDL(int index)
		{
			runnablesDL.remove(index);
		}
	
}
