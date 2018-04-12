/**
 * ValidateCode.java
 * @author qye.zheng
 * 
 * 	version 1.0
 */
package com.hua.validate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import com.hua.util.RandomUtil;
import com.hua.util.StringUtil;

/**
 * ValidateCode
 * 描述: 验证码
 * 生成随机验证码 : 纯数字、纯字母、数字字母混合
 * 
 * 以流的形式输出验证码结果
 * 
 * @author qye.zheng
 * 
 */
public final class ValidateCode
{
	/* 放到一个 session 中的key */
	public static final String RANDOM_CODE_KEY = "randomCodeKey";
	
	/* 随机验证码类型 : 纯数字(默认) */
	public static final int TYPE_NUMERIC = 0;
	
	/* 随机验证码类型 : 纯字母 */
	public static final int TYPE_ALPHA = 1;
	
	/* 随机验证码类型 : 数字字母混合 */
	public static final int TYPE_NUMERIC_ALPHA = 2;
	
	private static final int COLOR_LIMIT = 255;
	
	/* 随机验证码类型 */
	private int type = TYPE_NUMERIC;
	
	/* 是否绘制干扰线 (默认 true) */
	private boolean isDrawLine = true;
	
	/* 生成多少位 - 验证码 */
	private int digit = 5;
	
	/* 图像宽度 : px */
	private int width = 85;
	
	/* 图像高度 : px */
	private int height = 25;
	
	/* 干扰线数量  */
	private int lineSize = 24;
	
	/* 验证码 字体大小  */
	private int fontSize = 25;
	
	private int foregroundColor = 110;
	
	private int backgroundColor = 133;
	
	/* 随机字符串 */
	private String randomString;
	
	/* 图片格式, 默认 JPEG */
	private String format = "JPEG";
	
	//private String 
	
	public boolean getValidateCode(final OutputStream output) {
		try
		{
			// 缓冲区Image类
			final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
			// 绘制板
			final Graphics graphics = image.getGraphics();
			// 绘制方图
			graphics.fillRect(0, 0, width, height);
			// 绘制干扰线
			if (isDrawLine) {
				for (int i = 0; i < lineSize; i++) {
					drawLine(graphics);
				}
			}
			// 绘制随机字符串
			// 生成随机字符串
			randomString = getRandomString();
			for (int i = 0; i< randomString.length(); i++) {
				// 循环调用绘制单个码
				drawString(graphics, String.valueOf(randomString.charAt(i)), i + 1);
			}
			// 绘制处理
			graphics.dispose();
			// 将内存中的图片输出到指定的流
			ImageIO.write(image, format, output);
		} catch (Exception e)
		{
			e.printStackTrace();
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * 
	 * 描述: 绘制字符串 
	 * @author qye.zheng
	 * 
	 * @param graphics
	 * @param randomString
	 * @param index
	 * @return
	 */
	private void drawString(final Graphics graphics, final String randomString, final int index) {
		graphics.setFont(getFont());
		graphics.setColor(getRandomColor());
		graphics.translate(RandomUtil.nextInt(3), RandomUtil.nextInt(3));
		graphics.drawString(randomString, 13 * index, 16);
	}
	
	/**
	 * 
	 * 描述: 绘制干扰线
	 * @author qye.zheng
	 * 
	 * @param graphics
	 */
	private void drawLine(final Graphics graphics) {
		final int x1 = RandomUtil.nextInt(width);
		final int y1 = RandomUtil.nextInt(height);
		final int x2 = RandomUtil.nextInt(width - 5) + x1;
		final int y2 = RandomUtil.nextInt(height - 5) + y1;
		// 绘制干扰线
		graphics.drawLine(x1, y1, x2, y2);
	}
	
	/**
	 * 
	 * 描述: 获取字体
	 * @author qye.zheng
	 * 
	 * @return
	 */
	private Font getFont() {
		return new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, fontSize);
	}
	
	/**
	 * 
	 * 描述:  获取随机颜色
	 * @author qye.zheng
	 * 
	 * @return
	 */
	/**
	 * 
	 * 描述: 获取随机颜色
	 * @author qye.zheng
	 * 
	 * @param foregroundColor 前景色 [0, COLOR_LIMIT]
	 * @param backgroundColor 背景色 [0, COLOR_LIMIT]
	 * @return
	 */
	private Color getRandomColor() {
		final int red = foregroundColor + RandomUtil.nextInt(backgroundColor - foregroundColor - 16);
		final int green = foregroundColor + RandomUtil.nextInt(backgroundColor - foregroundColor - 14);
		final int blue = foregroundColor + RandomUtil.nextInt(backgroundColor - foregroundColor - 18);
		
		return new Color(red, green, blue);
	}
	
	/**
	 * 
	 * 描述: 
	 * @author qye.zheng
	 * 
	 * @return
	 */
	public String getRandomString() {
		if (!StringUtil.isEmpty(randomString)) {
			
			return randomString;
		}
		if (TYPE_NUMERIC == type) {
			// 生成数字
			randomString = RandomUtil.randomNumeric(digit);
		} else if (TYPE_ALPHA == type) {
			// 生成字母
			randomString = RandomUtil.randomAlphabetic(digit);
		} else if (TYPE_NUMERIC_ALPHA == type) {
			// 数字字母混合
			randomString = RandomUtil.randomAlphanumeric(digit);
		} else {
			// 未知验证码类型
			System.out.println("未知验证码类型");
		}
		
		return randomString;
	}

	/**
	 * @return the digit
	 */
	public int getDigit()
	{
		return digit;
	}

	/**
	 * @param digit the digit to set
	 */
	public void setDigit(int digit)
	{
		if (digit <= 0) {
			digit = 5;
		}
		
		this.digit = digit;
	}

	/**
	 * @return the type
	 */
	public int getType()
	{
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type)
	{
		this.type = type;
	}

	/**
	 * @return the width
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height)
	{
		this.height = height;
	}

	/**
	 * @return the lineSize
	 */
	public int getLineSize()
	{
		return lineSize;
	}

	/**
	 * @param lineSize the lineSize to set
	 */
	public void setLineSize(int lineSize)
	{
		this.lineSize = lineSize;
	}

	/**
	 * @return the fontSize
	 */
	public int getFontSize()
	{
		return fontSize;
	}

	/**
	 * @param fontSize the fontSize to set
	 */
	public void setFontSize(int fontSize)
	{
		this.fontSize = fontSize;
	}

	/**
	 * @return the foregroundColor
	 */
	public int getForegroundColor()
	{
		return foregroundColor;
	}

	/**
	 * @param foregroundColor the foregroundColor to set
	 */
	public void setForegroundColor(int foregroundColor)
	{
		if (foregroundColor > COLOR_LIMIT) {
			foregroundColor = COLOR_LIMIT;
		}
		this.foregroundColor = foregroundColor;
	}

	/**
	 * @return the backgroundColor
	 */
	public int getBackgroundColor()
	{
		return backgroundColor;
	}

	/**
	 * @param backgroundColor the backgroundColor to set
	 */
	public void setBackgroundColor(int backgroundColor)
	{
		if (backgroundColor > COLOR_LIMIT) {
			backgroundColor = COLOR_LIMIT;
		}
		this.backgroundColor = backgroundColor;
	}

	/**
	 * @return the isDrawLine
	 */
	public boolean isDrawLine()
	{
		return isDrawLine;
	}

	/**
	 * @param isDrawLine the isDrawLine to set
	 */
	public void setDrawLine(boolean isDrawLine)
	{
		this.isDrawLine = isDrawLine;
	}

	/**
	 * @return the format
	 */
	public String getFormat()
	{
		return format;
	}

	/**
	 * @param format the format to set
	 */
	public void setFormat(String format)
	{
		this.format = format;
	}
	
}
