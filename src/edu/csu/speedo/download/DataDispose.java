package edu.csu.speedo.download;

import java.math.BigDecimal;

//此类用于数据处理
public class DataDispose {
	
	//此方法用于将byte数据转换成Mb
	public static float turnToMb(int num)
	{
		BigDecimal numBd = new BigDecimal(
				(float) num/ 1024 / 1024);
		float returnNum = numBd.setScale(2, BigDecimal.ROUND_HALF_UP)
				.floatValue();
		return returnNum;
	}
	//此方法用于将byte数据转换成Kb
	public static float turnToKb(int num)
	{
		BigDecimal numBd = new BigDecimal(
				(float) num/ 1024);
		float returnNum = numBd.setScale(2, BigDecimal.ROUND_HALF_UP)
				.floatValue();
		return returnNum;
	}
	//此方法用于将秒转化为时分秒格式
	public static String turnToTimeFormat(int num)
	{
		int a,b,c;
		c=num/3600;
		b=(num-c*3600)/60;
		a=num-c*3600-b*60;
		
		if(c==0&&b==0)
		{
			String Time =new String(a+"秒");
			return Time;
		}else if(c==0){
			String Time =new String(b+"分"+a+"秒");
			return Time;
		}
		else
		{
		String Time =new String(c+"小时"+b+"分"+a+"秒");
		return Time;
		}
	}
	
	public static String turntoPercent(int numSon,int numMo)
	{
		int result =Math.round(100.0f*numSon/numMo);
		return new String(result+"%");
	}
	
}
