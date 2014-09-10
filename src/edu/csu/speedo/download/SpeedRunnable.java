package edu.csu.speedo.download;

import java.io.IOException;

import edu.csu.speedo.model.ModelUser;
import edu.csu.speedo.ui.MainWindow;

public class SpeedRunnable implements Runnable{

	int rowNum =0;
	public String src ="";
	public int fileSize =0;
	//1��ǰ��һ�����ļ���С
	int completeSize1 = 0;
	int completeSize2 = 0;
	//ʣ���ļ���С
	int remainSize = 0;
	boolean isComplete = false;
	boolean isParentStop = false;
	boolean isParentDestory = false;
	
	public void setIsParentDestory(boolean flag){
		System.out.println(321321);
		this.isParentDestory = flag;
		System.out.println(isParentDestory);
	}
	
	public void run()
	{
		try {
				while(!isComplete )
				{
					if(isParentDestory)//�жϸ��߳��Ƿ��Ѿ���ֹ
					{
						System.out.println("thread "+Thread.currentThread().getName()+" destory!!!!");
						Thread.currentThread().stop();
					}
					else 
					{
						completeSize1 = completeSize2;
						Thread.currentThread().sleep(1000);		
						//ˢ�¼�ʱ�ٶ�
						if(MainWindow.modelNum == 0&&!isParentDestory)
						{				
							rowNum =ModelUser.getRow(src);
							ModelUser.setValue( DataDispose.turnToKb((completeSize2 -completeSize1))+"kb/s",rowNum,4 );
						}
						else if(MainWindow.modelNum==1&&!isParentDestory)
						{
							rowNum =ModelUser.getRowDL(src);
							ModelUser.setValueDLModel( DataDispose.turnToKb((completeSize2 -completeSize1))+"kb/s",rowNum,4 );
						}
						
						//ˢ�½���
						if(MainWindow.modelNum == 0&&!isParentDestory)
						{				
							rowNum =ModelUser.getRow(src);
							
							ModelUser.setValue(DataDispose.turntoPercent(completeSize2,fileSize) ,rowNum,3 );
						}
						else if(MainWindow.modelNum==1&&!isParentDestory)
						{
							rowNum =ModelUser.getRowDL(src);
							ModelUser.setValueDLModel(DataDispose.turntoPercent(completeSize2,fileSize),rowNum,3 );
						}					
											
						//ˢ��ʣ��ʱ��
						if((completeSize2-completeSize1)!=0&&!isParentStop&&!isParentDestory)
						{//������粻�ж��Ҹ��߳�û��ֹͣ�Ҹ��߳�û�б��ݻ�
							if(MainWindow.modelNum == 0)
							{
								rowNum =ModelUser.getRow(src);
								ModelUser.setValue( DataDispose.turnToTimeFormat( remainSize/(completeSize2 -completeSize1) ) ,rowNum,5 );
							}
							else if(MainWindow.modelNum ==1)
							{
								rowNum =ModelUser.getRowDL(src);
								ModelUser.setValueDLModel( DataDispose.turnToTimeFormat( remainSize/(completeSize2 -completeSize1) ) ,rowNum,5 );
							}
						}
						else if(!isParentDestory)
						{//�����жϻ��߸��߳�ֹͣ �Ҹ��߳�û�б��ݻ�				
							ModelUser.setValue( "-----",rowNum,5 );
							continue;
						}
					}
				}
				if(isComplete==true)
				{
					ModelUser.setValue( 0+"kb/s",rowNum,4 );
					ModelUser.setValue( "-----",rowNum,5 );
					ModelUser.setValue( "�������",rowNum,0 );
					ModelUser.setValue("100%", rowNum, 3);
				}
		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}		

