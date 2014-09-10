package edu.csu.speedo.download;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Vector;

import edu.csu.speedo.model.ModelUser;

public class DownloadThread implements Runnable {

	String src; // URL
	String dest; // 存储路径
	String name; // 线程名
	boolean live = true; // 线程状态
	String loading = "0%"; // 进度
	float speedRate = 0; // 下载速度
	float remainingTime = 0; // 剩余时间
	int sumlen = 0; // 存储已下载的字节数
	int filelen = 0; // 文件大小 int型
	float fileSize = 0; // 文件大小 float型
	boolean stop = false; // 开始/暂停功能标识位
	int offset = 0; // 文件下载标识位，即文件下了多少B。
	String finish = "false"; // 文件下载完成标识，完成为true
	boolean destory = false; // runnable结束标志符，确定为true
	// 线程开始后已下完的文件大小，用于计算及时速度
	int completeSize = 0;
	
	public DownloadThread(String src, String dest, String name) {
		this.src = src;
		this.dest = dest;
		this.name = name;
	}

	@Override
	public void run() {
		try {
			// TODO Auto-generated method stub
			// 输出提示信息
			System.out.println("线程：" + name + "启动！");
			System.out.println("您的文件将会存储在：" + dest);
			URL url = new URL(src);
			String filename = new String(url.toString());
			System.out.println(url);
			// 提取URL中下载的文件名，有后缀
			filename = filename.substring(filename.lastIndexOf('/') + 1);
			System.out.println("文件名 :" + filename);
			System.out.println("主机名：" + url.getHost());
			System.out.println("端口号：" + url.getPort());
			System.out.println("协议：" + url.getProtocol());

			// 提取配置文件名，即去掉文件后缀
			String spdname = new String(filename.substring(0,
					filename.lastIndexOf('.')));

			// 写配置文件assignfile
			RandomAccessFile assignfile = new RandomAccessFile(dest + "\\"
					+ spdname + ".spd", "rw");

			// 获取配置文件的大小
			int assignfilelen = (int) assignfile.length();
			System.out.println("配置文件大小：" + assignfilelen);

			// 判断配置文件是否是新创建
			if (assignfilelen != 0) {// 否，获取配置文件中的offset
				byte[] afbytenum = new byte[assignfilelen];
				assignfile.read(afbytenum, 0, assignfilelen);
				String assignstr = new String(afbytenum);
				// 提取文件size
				fileSize = Float.parseFloat(assignstr.substring(5,
						assignstr.indexOf(' ')));
				filelen = (int) (fileSize * 1024 * 1024);
				// 提取offset
				offset = Integer.parseInt(assignstr.substring(assignstr
						.lastIndexOf(':') + 1));
				sumlen = sumlen + offset;
				completeSize =offset;
				// 计算已下载的文件大小，取小数点后两位，四舍五入

				float offsetsize = DataDispose.turnToMb(offset);
				System.out.println("此文件" + filename + "已经存在，已下载了" + offsetsize
						+ "MB");
				System.out.println("Speedo下载器将继续为您下载，请稍候...");

			} else {// 是，创建一个offset字符串
				// 此URL为了获取下载文件大小
				URL url2 = new URL(src);
				HttpURLConnection conn2 = (HttpURLConnection) url2
						.openConnection();
				conn2.setRequestProperty("Range", "bytes=0-");
				filelen = conn2.getContentLength();
				fileSize = DataDispose.turnToMb(filelen);
				// 写配置文件
				assignfile.writeBytes("size:" + fileSize + " " + "offset:"
						+ sumlen);
			}

			// 新建一个存储线程信息的Vector
			Vector date = new Vector();
			date.add("正在下载"); // 0下载状态
			date.add(name); // 1文件名
			date.add(fileSize + "MB"); // 2文件大小
			date.add(loading); // 3下载进度
			date.add(speedRate + "kb/s"); // 4下载速度
			date.add(0 + "s"); // 5剩余时间

			Vector resourceInf = new Vector(); // 存储资源信息
			resourceInf.add(src); // 0资源url
			resourceInf.add(dest); // 1目标路径
			resourceInf.add(spdname); // 2配置文件名，去掉后缀后的纯文件名
			resourceInf.add(name); // 3文件名
			resourceInf.add(finish); // 4下载是否完成

			// 往容器队列中添加子容器
			ModelUser.addVector(date);
			// 刷新model
			ModelUser.addRow(date);
			ModelUser.addRowDLModel(date);
			// 往urls中添加url
			ModelUser.addUrl(src);
			ModelUser.addUrlDL(src);
			// 往resourceInf中添加对象
			ModelUser.addResInf(resourceInf);
			// 往Runnable容器添加当前对象
			ModelUser.addRunn(this);
			// 往RunablesDL容器中添加当前对象
			ModelUser.addRunnDL(this);
			// 网络连接开始
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Range", "bytes= " + offset + "-");
			// 获取即将下载的文件的大小，取小数点后两位，四舍五入

			float remainSize = DataDispose.turnToMb(conn.getContentLength());
			System.out.println("即将下载的文件大小：" + remainSize + "MB");
			int code = conn.getResponseCode();
			System.out.println("返回状态码：" + code);

			if (code == 206) {
				System.out.println("连接成功，开始传输文件...");
				RandomAccessFile file = new RandomAccessFile(dest + "\\"
						+ filename, "rw");
				file.seek((long) offset);
				InputStream is = conn.getInputStream();
				BufferedInputStream bis = new BufferedInputStream(is);
				byte[] bt = new byte[1024];
				int len = 0;

				SpeedRunnable sr = new SpeedRunnable();
				sr.src = src;
				sr.fileSize = filelen;
				Thread srThread = new Thread(sr);
				srThread.start();
				len = bis.read(bt);
				// 传输数据
				while (len != -1) {
					if (destory) {
						file.close();
						assignfile.close();
						sr.setIsParentDestory(true); // 告知SpeedRunnable父线程已经停止，请求子进程停止
						try {
							System.out.println(1);
							Thread.currentThread().sleep(1000);
							System.out.println(2);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println("thread " + name + " destory!");
						Thread.currentThread().stop();
					}
					sr.isParentStop = stop;
					if (!stop) {
						file.write(bt, 0, len);
						// 此文件总共已经下载的大小
						sumlen = sumlen + len;
						// 即时已下载的大小
						completeSize += len;
						// 刷新已下载大小，用于传递给速度计算线程，每秒计算1次
						sr.completeSize2 = completeSize;
						// 刷新剩余文件大小
						sr.remainSize = filelen - sumlen;
						// 刷新配置文件
						assignfile.seek(0); // 这里把这行放到下一行后面会出错！ 请老师指导下。
						assignfile.writeBytes("size:" + fileSize + " "
								+ "offset:" + String.valueOf(sumlen));
						len = bis.read(bt);
						System.out.println("downloading");
					}
				}

				file.close();
				assignfile.close();
				// 文件下载完成
				finish = "true";
				// 速度刷新线程中止
				sr.isComplete = true;
				// 在resourceInf中将任务标识为已完成
				resourceInf.set(4, finish);
				// 添加已完成列表的信息
				date.set(3, "100%");
				date.set(4, "0k/s");
				date.set(0, "下载完成");
				date.set(5, "-----");
				ModelUser.addUrlFS(src);
				ModelUser.addRowFSModel(date);
				try {// 让线程休眠1秒，等待子线程刷新完
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// 移除正在下载列表的中任务信息
				int removeDlId = ModelUser.getRowDL(src);
				ModelUser.removeRowDLModel(removeDlId);
				ModelUser.removeUrlDL(removeDlId);
				ModelUser.removeRunnableDL(removeDlId);

				// 文件下完后删除配置文件
				File deleteFile = new File(dest + "\\" + spdname + ".spd");
				deleteFile.delete();
				System.out.println("传输完毕!");
				// 播放提示音
				new VoicePlay().playvoice();

			} else
				System.out.println("连接错误。");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			System.out.println("inner finally");
		}
	}

	// 线程中止方法
	public void terminate() {
		stop = true;
		System.out.println("暂停暂停暂停暂停暂停暂停暂停暂停暂停");
	}

	// 线程开始方法
	public void restart() {
		stop = false;
	}

	public void setDestory() {
		System.out.println("123123123");
		destory = true;
	}
}
