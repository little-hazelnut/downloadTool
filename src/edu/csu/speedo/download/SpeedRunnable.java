package edu.csu.speedo.download;

import java.io.IOException;

import edu.csu.speedo.model.ModelUser;
import edu.csu.speedo.ui.MainWindow;

public class SpeedRunnable implements Runnable{

	int rowNum =0;
	public String src ="";
	public int fileSize =0;
	//1秒前与一秒后的文件大小
	int completeSize1 = 0;
	int completeSize2 = 0;
	//剩余文件大小
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
					if(isParentDestory)//判断父线程是否已经中止
					{
						System.out.println("thread "+Thread.currentThread().getName()+" destory!!!!");
						Thread.currentThread().stop();
					}
					else 
					{
						completeSize1 = completeSize2;
						Thread.currentThread().sleep(1000);		
						//刷新即时速度
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
						
						//刷新进度
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
											
						//刷新剩余时间
						if((completeSize2-completeSize1)!=0&&!isParentStop&&!isParentDestory)
						{//如果网络不中断且父线程没有停止且父线程没有被摧毁
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
						{//网络中断或者父线程停止 且父线程没有被摧毁				
							ModelUser.setValue( "-----",rowNum,5 );
							continue;
						}
					}
				}
				if(isComplete==true)
				{
					ModelUser.setValue( 0+"kb/s",rowNum,4 );
					ModelUser.setValue( "-----",rowNum,5 );
					ModelUser.setValue( "下载完成",rowNum,0 );
					ModelUser.setValue("100%", rowNum, 3);
				}
		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}		

