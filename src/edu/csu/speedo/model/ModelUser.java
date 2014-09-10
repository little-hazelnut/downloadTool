package edu.csu.speedo.model;

import java.io.Serializable;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import edu.csu.speedo.download.DownloadThread;

public class ModelUser implements Serializable {
	
	//ȫ�������Model
	public static DefaultTableModel dateModel = new DefaultTableModel(
			new Object[][] {
			}, new String[] { "״̬", "�ļ���", "��С", "����", "�ٶ�", "ʣ��ʱ��" })
	{
		boolean[] canEdit = new boolean[] { false, false, false, false,
				false, false
		};
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return canEdit[columnIndex];
		}
	};
	
	//�������������Model
	public static DefaultTableModel dateModelDownloading = new DefaultTableModel(
			new Object[][] {
			}, new String[] { "״̬", "�ļ���", "��С", "����", "�ٶ�", "ʣ��ʱ��" })
	{
		boolean[] canEdit = new boolean[] { false, false, false, false,
				false, false
		};
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return canEdit[columnIndex];
		}
	};
	
	//�Ѿ���������Model
	public static DefaultTableModel dateModelFinish = new DefaultTableModel(
			new Object[][] {
			}, new String[] { "״̬", "�ļ���", "��С", "����", "�ٶ�", "ʣ��ʱ��" })
	{
		boolean[] canEdit = new boolean[] { false, false, false, false,
				false, false
		};
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return canEdit[columnIndex];
		}
	};
	
	
	
	//����һ���洢ȫ��������������Ϣ��Vector���洢����src ��dest��finish��vector
	public static Vector<Vector> allDownloadDateInf = new Vector<Vector>();
	//����һ���洢ȫ�����������ݵ�Vecot���洢�����б�ÿһ����Ϣ
	public static Vector<Vector> allDownloadDate = new Vector<Vector>();
	//����һ���洢ȫ���߳�URL��vector
	public static Vector<String> urls = new Vector<String>();
	//����һ���洢�������߳�URL��vector
	public static Vector<String> urlsDownloading = new Vector<String>();
	//����һ���洢��������߳�URL��vector
	public static Vector<String> urlsFinish = new Vector<String>();
	//����һ���洢ȫ������URL��Ӧ��Runnable
	public static Vector<DownloadThread> runnables =new Vector<DownloadThread>();
	//����һ���洢������������URL��Ӧ��Runnable
	public static Vector<DownloadThread> runnablesDL =new Vector<DownloadThread>();
	//Mode��ʼ������
	public static void modelDisplay() {
		for (int i = 0; i < allDownloadDate.size(); i++) {
			dateModel.addRow(allDownloadDate.get(i));
		}
	}
	
		//urls���������url�������������Ԫ�أ�ÿ��Ԫ����һ��url
		public static void addUrl(String urlName)
		{
			urls.add(urlName);
		}
		
		//�Ƴ�urls������ָ��Ԫ��
		public static void removeUrl(int index)
		{
			urls.remove(index);
		}
		
		//��ȡĳ���߳���ȫ������Table�ж�Ӧurl
		public static String getRowUrl(int index)
		{
			return urls.get(index);
		}
		
		//��ȡĳ���߳���ȫ������Table�ж�Ӧ���к�
		public static int getRow(String urlId)
		{
			return urls.indexOf(urlId);
		}
	
		//urlsDownload���������url�������������Ԫ�أ�ÿ��Ԫ����һ��url
		public static void addUrlDL(String urlName)
		{
			urlsDownloading.add(urlName);
		}
		
		//��ȡĳ���߳�����������Table�ж�Ӧ���к�
		public static int getRowDL(String urlId)
		{
			return urlsDownloading.indexOf(urlId);
		}
		
		//��ȡĳ���߳�����������Table�ж�Ӧurl
		public static String getRowUrlDL(int index)
		{
			return urlsDownloading.get(index);
		}
		
		//�Ƴ�urlsDownload������ָ��Ԫ��
		public static void removeUrlDL(int index)
		{
			urlsDownloading.remove(index);
		}
		
		
		//urlsFinish���������url�������������Ԫ�أ�ÿ��Ԫ����һ��url
		public static void addUrlFS(String urlName)
		{
			urlsFinish.add(urlName);
		}
		
		//��ȡĳ���߳����������Table�ж�Ӧ���к�
		public static int getRowFS(String urlId)
		{
			return urlsFinish.indexOf(urlId);
		}
		
		//��ȡĳ���߳����������Table�ж�Ӧurl
		public static String getRowUrlFS(int index)
		{
			return urlsFinish.get(index);
		}
		
		//�Ƴ�urlsFinish������ָ��Ԫ��
		public static void removeUrlFS(int index)
		{
			urlsFinish.remove(index);
		}
			
		//Vector���������vector�������������Ԫ�أ�ÿ��Ԫ����һ��resoureInf Vector
		public static void addResInf(Vector vector)
		{
			allDownloadDateInf.add(vector);
		}
		
		//Vector�������Ƴ�ָ��Ԫ�أ�ÿ��Ԫ����һ��resoureInf Vector
		public static void removeResInf(int index)
		{
			allDownloadDateInf.remove(index);
		}
	
		//Vector�����л�ȡָ��Ԫ�أ�ÿ��Ԫ����һ��resoureInf Vector
		public static Vector getResInf(int index)
		{
			return allDownloadDateInf.get(index);
		}
			
		//��������������б�����Ϣ�����������������Ԫ�أ�ÿ��Ԫ����һ������
		public static void addVector(Vector vector)
		{
			allDownloadDate.add(vector);
		}

		//������ɾ�������б�����Ϣ������ÿ��Ԫ����һ������
		public static void removeVector(int index)
		{
			allDownloadDate.remove(index);
		}
	
		//ˢ��dataModel����,����������һ����Ϣ
		public static void addRow(Vector vector)
		{
			dateModel.addRow(vector);
		}
		
		//�Ƴ�dataModel�е�ָ����
		public static void removeRow(int num)
		{
			dateModel.removeRow(num);
		}
	
		//����dataModel�ж�Ӧĳ�к��ж�Ӧ�ĵ�Ԫ����ֵ
		public static void setValue(Object obj,int row,int column)
		{
			dateModel.setValueAt(obj, row, column);
		}
		
		//ˢ��dataModelDownloading����,����������һ����Ϣ
		public static void addRowDLModel(Vector vector)
		{
			dateModelDownloading.addRow(vector);
		}
		
		//�Ƴ�dataModelDownloading�е�ָ����
		public static void removeRowDLModel(int num)
		{
			dateModelDownloading.removeRow(num);
		}
	
		//����dataModelDownloading�ж�Ӧĳ�к��ж�Ӧ�ĵ�Ԫ����ֵ
		public static void setValueDLModel(Object obj,int row,int column)
		{
			dateModelDownloading.setValueAt(obj, row, column);
		}
		
		//ˢ��dataModelFinish����,����������һ����Ϣ
		public static void addRowFSModel(Vector vector)
		{
			dateModelFinish.addRow(vector);
		}
				
		//�Ƴ�dataModelFinish�е�ָ����
		public static void removeRowFSModel(int num)
		{
			dateModelFinish.removeRow(num);
		}
			
		//����dataModelFinish�ж�Ӧĳ�к��ж�Ӧ�ĵ�Ԫ����ֵ
		public static void setValueFSModel(Object obj,int row,int column)
		{
			dateModelFinish.setValueAt(obj, row, column);
		}
		
		//ȫ������runnables
		//��Runnable��������Ӷ���ķ���
		public static void addRunn(DownloadThread Runn)
		{
			runnables.add(Runn);
		}
		
		//��Runnable�����л�ȡ����ķ���
		public static DownloadThread getRunn(int num)
		{
			return runnables.get(num);
		}
			
		//�Ƴ�runnables������ָ��Ԫ��
		public static void removeRunnable(int index)
		{
			runnables.remove(index);
		}
		
		//��������runnableDL
		//��Runnable��������Ӷ���ķ���
		public static void addRunnDL(DownloadThread Runn)
		{
			runnablesDL.add(Runn);
		}
				
		//��Runnable�����л�ȡ����ķ���
		public static DownloadThread getRunnDL(int num)
		{
			return runnablesDL.get(num);
		}
					
		//�Ƴ�runnables������ָ��Ԫ��
		public static void removeRunnableDL(int index)
		{
			runnablesDL.remove(index);
		}
	
}
