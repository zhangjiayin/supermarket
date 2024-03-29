package com.linkwee.xoss.helper;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
public class MatrixToImageWriterEx {
	
//	private static final MatrixToLogoImageConfig DEFAULT_CONFIG = new MatrixToLogoImageConfig();
	
	/**
	 * 根据内容生成二维码数据
	 * @param content 二维码文字内容[为了信息安全性，一般都要先进行数据加密]
	 * @param width 二维码照片宽度
	 * @param height 二维码照片高度
	 * @return
	 */
	public static BitMatrix createQRCode(String content, int width, int height) throws Exception{
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();   
		//设置字符编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        hints.put(EncodeHintType.MARGIN,0);
        // 指定纠错等级
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        BitMatrix matrix = null;  
        matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints); 
        return updateBit(matrix);
	}
	private static BitMatrix updateBit(BitMatrix matrix){
		int[] rec = matrix.getEnclosingRectangle();  
		int resWidth = rec[2] + 1;  
		int resHeight = rec[3] + 1;  
		BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);  
		resMatrix.clear();  
		for (int i = 0; i < resWidth; i++) {  
		    for (int j = 0; j < resHeight; j++) {  
		        if (matrix.get(i + rec[0], j + rec[1])) { 
		             resMatrix.set(i, j); 
		        } 
		    }  
		} 
		return resMatrix;
	}
//	/**
//	 * 写入二维码、以及将照片logo写入二维码中
//	 * @param matrix 要写入的二维码
//	 * @param format 二维码照片格式
//	 * @param imagePath 二维码照片保存路径
//	 * @param logoPath logo路径
//	 * @throws IOException
//	 */
//	@SuppressWarnings("deprecation")
//	private static void writeToFile(BitMatrix matrix, String format, String imagePath, String logoPath) throws IOException {
//		MatrixToImageWriter.writeToFile(matrix, format, new File(imagePath), new MatrixToImageConfig());
//		
//		//添加logo图片, 此处一定需要重新进行读取，而不能直接使用二维码的BufferedImage 对象
//		BufferedImage img = ImageIO.read(new File(imagePath));
//		MatrixToImageWriterEx.overlapImage(img, format, imagePath, logoPath, DEFAULT_CONFIG);
//	}
	/**
	 * 写入二维码、以及将照片logo写入二维码中
	 * @param matrix 要写入的二维码
	 * @param format 二维码照片格式
	 * @param imagePath 二维码照片保存路径
	 * @param logoPath logo路径
						 * @param logoConfig logo配置对象
						 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	private static void writeToFile(BitMatrix matrix, String format, String imagePath, String logoPath, MatrixToLogoImageConfig logoConfig, String loginName) throws Exception {
		MatrixToImageWriter.writeToFile(matrix, format, new File(imagePath), new MatrixToImageConfig());
		//添加logo图片, 此处一定需要重新进行读取，而不能直接使用二维码的BufferedImage 对象
		BufferedImage img = ImageIO.read(new File(imagePath));
		MatrixToImageWriterEx.overlapImage(img, format, imagePath, logoPath, logoConfig, loginName);
	}
	
	

	/**
	 * 将照片logo添加到二维码中间
	 * @param image 生成的二维码照片对象
	 * @param imagePath 照片保存路径
	 * @param logoPath logo照片路径
	 * @param formate 照片格式
	 */
	private static void overlapImage(BufferedImage image, String formate, String imagePath, String logoPath, MatrixToLogoImageConfig logoConfig , String loginName) throws Exception{
		BufferedImage logo = ImageIO.read(new File(logoPath));
		Graphics2D g = image.createGraphics();
		//考虑到logo照片贴到二维码中，建议大小不要超过二维码的1/5;
		int width = image.getWidth() / logoConfig.getLogoPart();
		int height = image.getHeight() / logoConfig.getLogoPart();
		//logo起始位置，此目的是为logo居中显示
		int x = (image.getWidth() - width) / 2;
		int y = (image.getHeight() - height) / 2;
		//绘制图
		g.drawImage(logo, x, y, width, height, null);
		
		//给logo画边框
		//构造一个具有指定线条宽度以及 cap 和 join 风格的默认值的实心 BasicStroke
		g.setStroke(new BasicStroke(logoConfig.getBorder()));
		g.setColor(logoConfig.getBorderColor());
		g.drawRect(x, y, width, height);
		
		//g.setColor(Color.black);
		if(loginName!=null){
			g.drawString(loginName, (image.getWidth()-11*6)/2, image.getHeight()-10);
		}
		g.dispose();
		//写入logo照片到二维码
		ImageIO.write(image, formate, new File(imagePath));
	}
	
	/**
	 * 
	 * @param qrPath
	 * @param logoPath
	 * @param content
	 * @param loginName
	 * @param width
	 * @param height
	 */
	public static void createQR(String qrPath,String imgType, String logoPath, String content, String loginName, int width, int height) throws Exception{
		BitMatrix matrix = MatrixToImageWriterEx.createQRCode(content, width, height);
		if(logoPath==null){
			File outputFile = new File(qrPath);
			MatrixToImageWriter.writeToPath(matrix,imgType, outputFile.toPath());
		}else{
			MatrixToLogoImageConfig logoConfig = new MatrixToLogoImageConfig(Color.white, 4);
			MatrixToImageWriterEx.writeToFile(matrix,imgType, qrPath, logoPath, logoConfig, loginName);
		}
	}
	
}
