/*
 * NewDownload.java
 *
 * Created on __DATE__, __TIME__
 */

package edu.csu.speedo.ui;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import edu.csu.speedo.download.Download;
import edu.csu.speedo.model.ModelUser;

public class NewDownload extends javax.swing.JFrame {

	String storePath;
	String urlName;

	/** Creates new form NewDownload */
	public NewDownload() {
		setWindowIcon();
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField();
		jTextField2 = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();

		// setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("\u65b0\u5efa\u4efb\u52a1");
		setResizable(false);
		this.addWindowListener(new JFrameCloseListener());

		jLabel1.setText("\u8bf7\u8f93\u5165\u4e0b\u8f7dURL\uff1a");

		jLabel2.setText("\u8bf7\u8f93\u5165\u6587\u4ef6\u5b58\u50a8\u8def\u5f84\uff1a");

		jTextField1.setForeground(new java.awt.Color(153, 153, 153));
		jTextField1
				.setText("<\u8bf7\u8f93\u5165\u4e00\u4e2a\u5408\u6cd5\u7684\u4e0b\u8f7d\u5730\u5740>");
		jTextField1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jTextField1MouseClicked(evt);
			}
		});
		jTextField1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1ActionPerformed(evt);
			}
		});

		jTextField2.setEditable(false);
		jTextField2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField2ActionPerformed(evt);
			}
		});

		jButton1.setIcon(new javax.swing.ImageIcon("img/open.png")); // NOI18N
		jButton1.setText("\u786e\u5b9a");
		jButton1.setFont(new java.awt.Font("微软雅黑", 1, 14));
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					jButton1ActionPerformed(evt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		jButton2.setIcon(new javax.swing.ImageIcon("img/cancle.png")); // NOI18N
		jButton2.setFont(new java.awt.Font("微软雅黑", 1, 14));
		jButton2.setText("\u53d6\u6d88");
		jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButton2MouseClicked(evt);
			}
		});

		// jButton3.setIcon(new javax.swing.ImageIcon(
		// "img/open.png")); // NOI18N
		jButton3.setText("\u6d4f\u89c8");
		jButton3.setFont(new java.awt.Font("微软雅黑", 1, 14));
		jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jButton3MouseClicked(evt);
			}
		});
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(12, 12,
																		12)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jTextField2,
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						279,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addGroup(
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						layout.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																								.addGroup(
																										layout.createSequentialGroup()
																												.addGap(40,
																														40,
																														40)
																												.addComponent(
																														jTextField1,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														277,
																														javax.swing.GroupLayout.PREFERRED_SIZE))
																								.addComponent(
																										jLabel2)
																								.addComponent(
																										jLabel1,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										142,
																										javax.swing.GroupLayout.PREFERRED_SIZE)))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		jButton3))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(69, 69,
																		69)
																.addComponent(
																		jButton1)
																.addGap(55, 55,
																		55)
																.addComponent(
																		jButton2)))
								.addContainerGap(14, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(28, 28, 28)
								.addComponent(jLabel1)
								.addGap(5, 5, 5)
								.addComponent(jTextField1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										31,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel2)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jButton3)
												.addComponent(
														jTextField2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														28,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jButton1)
												.addComponent(jButton2))
								.addContainerGap(22, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>
		// GEN-END:initComponents

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		// 取消键促发事件
		this.dispose();
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)
			throws IOException {
		// TODO add your handling code here:
		// 确定按钮
		// 创建下载类的对象
		Download down = new Download();
		// 获取要下载的源和目标
		// storePath目标路径
		// urlName URL
		// threadName 线程名，urlName提取
		urlName = this.jTextField1.getText();
		storePath = this.jTextField2.getText();
		String threadName = urlName.substring(urlName.lastIndexOf('/') + 1);

		// 此处判断输入URL是否不合法
		// 判断Url是否已经存在
		int urlIndex = ModelUser.getRow(urlName);
		if (urlIndex != -1) {
			System.out.println("Url已经在下载列表中存在！");
			JOptionPane.showMessageDialog(jButton1, "您输入的Url已经在下载列表中存在！", "提示",
					JOptionPane.ERROR_MESSAGE);

		} else {
			// 调用下载方法
			down.down(urlName, storePath, threadName);
			this.dispose();
		}
	}
	private void setWindowIcon() {
		Image windowIcon = Toolkit.getDefaultToolkit().getImage(
				"img/open.png");
		setIconImage(windowIcon);
	}


	private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		// 浏览文件夹获取文件夹路径按钮
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		// 打开文件对话框
		chooser.showOpenDialog(jButton3);
		// 获取选择的文件
		File file = chooser.getSelectedFile();
		if (file == null) {
			storePath = "";
		} else
			storePath = file.getAbsolutePath();
		jTextField2.setText(storePath);
	}

	class JFrameCloseListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			// saveModel();
			dispose();
		}
	}

	private void jTextField1MouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		jTextField1.setText("");
	}

	private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:

	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new NewDownload().setVisible(true);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField2;
	// End of variables declaration//GEN-END:variables

}