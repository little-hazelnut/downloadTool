package edu.csu.speedo.download;

import java.math.BigDecimal;

//�����������ݴ���
public class DataDispose {
	
	//�˷������ڽ�byte����ת����Mb
	public static float turnToMb(int num)
	{
		BigDecimal numBd = new BigDecimal(
				(float) num/ 1024 / 1024);
		float returnNum = numBd.setScale(2, BigDecimal.ROUND_HALF_UP)
				.floatValue();
		return returnNum;
	}
	//�˷������ڽ�byte����ת����Kb
	public static float turnToKb(int num)
	{
		BigDecimal numBd = new BigDecimal(
				(float) num/ 1024);
		float returnNum = numBd.setScale(2, BigDecimal.ROUND_HALF_UP)
				.floatValue();
		return returnNum;
	}
	//�˷������ڽ���ת��Ϊʱ�����ʽ
	public static String turnToTimeFormat(int num)
	{
		int a,b,c;
		c=num/3600;
		b=(num-c*3600)/60;
		a=num-c*3600-b*60;
		
		if(c==0&&b==0)
		{
			String Time =new String(a+"��");
			return Time;
		}else if(c==0){
			String Time =new String(b+"��"+a+"��");
			return Time;
		}
		else
		{
		String Time =new String(c+"Сʱ"+b+"��"+a+"��");
		return Time;
		}
	}
	
	public static String turntoPercent(int numSon,int numMo)
	{
		int result =Math.round(100.0f*numSon/numMo);
		return new String(result+"%");
	}
	
}
