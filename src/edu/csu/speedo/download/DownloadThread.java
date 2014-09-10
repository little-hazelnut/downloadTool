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
	String dest; // �洢·��
	String name; // �߳���
	boolean live = true; // �߳�״̬
	String loading = "0%"; // ����
	float speedRate = 0; // �����ٶ�
	float remainingTime = 0; // ʣ��ʱ��
	int sumlen = 0; // �洢�����ص��ֽ���
	int filelen = 0; // �ļ���С int��
	float fileSize = 0; // �ļ���С float��
	boolean stop = false; // ��ʼ/��ͣ���ܱ�ʶλ
	int offset = 0; // �ļ����ر�ʶλ�����ļ����˶���B��
	String finish = "false"; // �ļ�������ɱ�ʶ�����Ϊtrue
	boolean destory = false; // runnable������־����ȷ��Ϊtrue
	// �߳̿�ʼ����������ļ���С�����ڼ��㼰ʱ�ٶ�
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
			// �����ʾ��Ϣ
			System.out.println("�̣߳�" + name + "������");
			System.out.println("�����ļ�����洢�ڣ�" + dest);
			URL url = new URL(src);
			String filename = new String(url.toString());
			System.out.println(url);
			// ��ȡURL�����ص��ļ������к�׺
			filename = filename.substring(filename.lastIndexOf('/') + 1);
			System.out.println("�ļ��� :" + filename);
			System.out.println("��������" + url.getHost());
			System.out.println("�˿ںţ�" + url.getPort());
			System.out.println("Э�飺" + url.getProtocol());

			// ��ȡ�����ļ�������ȥ���ļ���׺
			String spdname = new String(filename.substring(0,
					filename.lastIndexOf('.')));

			// д�����ļ�assignfile
			RandomAccessFile assignfile = new RandomAccessFile(dest + "\\"
					+ spdname + ".spd", "rw");

			// ��ȡ�����ļ��Ĵ�С
			int assignfilelen = (int) assignfile.length();
			System.out.println("�����ļ���С��" + assignfilelen);

			// �ж������ļ��Ƿ����´���
			if (assignfilelen != 0) {// �񣬻�ȡ�����ļ��е�offset
				byte[] afbytenum = new byte[assignfilelen];
				assignfile.read(afbytenum, 0, assignfilelen);
				String assignstr = new String(afbytenum);
				// ��ȡ�ļ�size
				fileSize = Float.parseFloat(assignstr.substring(5,
						assignstr.indexOf(' ')));
				filelen = (int) (fileSize * 1024 * 1024);
				// ��ȡoffset
				offset = Integer.parseInt(assignstr.substring(assignstr
						.lastIndexOf(':') + 1));
				sumlen = sumlen + offset;
				completeSize =offset;
				// ���������ص��ļ���С��ȡС�������λ����������

				float offsetsize = DataDispose.turnToMb(offset);
				System.out.println("���ļ�" + filename + "�Ѿ����ڣ���������" + offsetsize
						+ "MB");
				System.out.println("Speedo������������Ϊ�����أ����Ժ�...");

			} else {// �ǣ�����һ��offset�ַ���
				// ��URLΪ�˻�ȡ�����ļ���С
				URL url2 = new URL(src);
				HttpURLConnection conn2 = (HttpURLConnection) url2
						.openConnection();
				conn2.setRequestProperty("Range", "bytes=0-");
				filelen = conn2.getContentLength();
				fileSize = DataDispose.turnToMb(filelen);
				// д�����ļ�
				assignfile.writeBytes("size:" + fileSize + " " + "offset:"
						+ sumlen);
			}

			// �½�һ���洢�߳���Ϣ��Vector
			Vector date = new Vector();
			date.add("��������"); // 0����״̬
			date.add(name); // 1�ļ���
			date.add(fileSize + "MB"); // 2�ļ���С
			date.add(loading); // 3���ؽ���
			date.add(speedRate + "kb/s"); // 4�����ٶ�
			date.add(0 + "s"); // 5ʣ��ʱ��

			Vector resourceInf = new Vector(); // �洢��Դ��Ϣ
			resourceInf.add(src); // 0��Դurl
			resourceInf.add(dest); // 1Ŀ��·��
			resourceInf.add(spdname); // 2�����ļ�����ȥ����׺��Ĵ��ļ���
			resourceInf.add(name); // 3�ļ���
			resourceInf.add(finish); // 4�����Ƿ����

			// ���������������������
			ModelUser.addVector(date);
			// ˢ��model
			ModelUser.addRow(date);
			ModelUser.addRowDLModel(date);
			// ��urls�����url
			ModelUser.addUrl(src);
			ModelUser.addUrlDL(src);
			// ��resourceInf����Ӷ���
			ModelUser.addResInf(resourceInf);
			// ��Runnable������ӵ�ǰ����
			ModelUser.addRunn(this);
			// ��RunablesDL��������ӵ�ǰ����
			ModelUser.addRunnDL(this);
			// �������ӿ�ʼ
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Range", "bytes= " + offset + "-");
			// ��ȡ�������ص��ļ��Ĵ�С��ȡС�������λ����������

			float remainSize = DataDispose.turnToMb(conn.getContentLength());
			System.out.println("�������ص��ļ���С��" + remainSize + "MB");
			int code = conn.getResponseCode();
			System.out.println("����״̬�룺" + code);

			if (code == 206) {
				System.out.println("���ӳɹ�����ʼ�����ļ�...");
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
				// ��������
				while (len != -1) {
					if (destory) {
						file.close();
						assignfile.close();
						sr.setIsParentDestory(true); // ��֪SpeedRunnable���߳��Ѿ�ֹͣ�������ӽ���ֹͣ
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
						// ���ļ��ܹ��Ѿ����صĴ�С
						sumlen = sumlen + len;
						// ��ʱ�����صĴ�С
						completeSize += len;
						// ˢ�������ش�С�����ڴ��ݸ��ٶȼ����̣߳�ÿ�����1��
						sr.completeSize2 = completeSize;
						// ˢ��ʣ���ļ���С
						sr.remainSize = filelen - sumlen;
						// ˢ�������ļ�
						assignfile.seek(0); // ��������зŵ���һ�к������� ����ʦָ���¡�
						assignfile.writeBytes("size:" + fileSize + " "
								+ "offset:" + String.valueOf(sumlen));
						len = bis.read(bt);
						System.out.println("downloading");
					}
				}

				file.close();
				assignfile.close();
				// �ļ��������
				finish = "true";
				// �ٶ�ˢ���߳���ֹ
				sr.isComplete = true;
				// ��resourceInf�н������ʶΪ�����
				resourceInf.set(4, finish);
				// ���������б����Ϣ
				date.set(3, "100%");
				date.set(4, "0k/s");
				date.set(0, "�������");
				date.set(5, "-----");
				ModelUser.addUrlFS(src);
				ModelUser.addRowFSModel(date);
				try {// ���߳�����1�룬�ȴ����߳�ˢ����
					Thread.currentThread().sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// �Ƴ����������б����������Ϣ
				int removeDlId = ModelUser.getRowDL(src);
				ModelUser.removeRowDLModel(removeDlId);
				ModelUser.removeUrlDL(removeDlId);
				ModelUser.removeRunnableDL(removeDlId);

				// �ļ������ɾ�������ļ�
				File deleteFile = new File(dest + "\\" + spdname + ".spd");
				deleteFile.delete();
				System.out.println("�������!");
				// ������ʾ��
				new VoicePlay().playvoice();

			} else
				System.out.println("���Ӵ���");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			System.out.println("inner finally");
		}
	}

	// �߳���ֹ����
	public void terminate() {
		stop = true;
		System.out.println("��ͣ��ͣ��ͣ��ͣ��ͣ��ͣ��ͣ��ͣ��ͣ");
	}

	// �߳̿�ʼ����
	public void restart() {
		stop = false;
	}

	public void setDestory() {
		System.out.println("123123123");
		destory = true;
	}
}
