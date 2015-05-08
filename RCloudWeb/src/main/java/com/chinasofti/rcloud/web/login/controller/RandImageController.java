package com.chinasofti.rcloud.web.login.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.rcloud.web.common.CommonConstant;
import com.chinasofti.rcloud.web.common.RequestMappingName;


@Controller

@RequestMapping(value = "/randImage")
public class RandImageController {
	
	@RequestMappingName(CommonConstant.ROLE_PERMISSION_COMMON)
	@RequestMapping(value = "/getRandImage" , method = RequestMethod.GET)
	@ResponseBody
	public void getRandImage(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		Random random = new Random();
		response.setContentType("image/jpeg");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0L);
		HttpSession session = request.getSession();

		int width = 80;
		int height = 25;
		BufferedImage image = new BufferedImage(width, height, 1);

		Graphics g = image.getGraphics();

		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);

		g.setFont(new Font("Times New Roman", 5, 22));

		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++)
		{
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl =random.nextInt(18);
			int yl =random.nextInt(18);
			g.drawLine(x, y, x + xl, y + yl);
		}

		String sRand = "";
		for (int i = 0; i < 4; i++)
		{
			String rand = String.valueOf(random.nextInt(10));
			sRand = sRand + rand;

			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 18 * i + 8, 18);
		}

		session.setAttribute(CommonConstant.CHECK_CODE, sRand);
		long currentTime = System.currentTimeMillis();
		session.setAttribute(CommonConstant.CHECK_CODE_CREATE_TIME, currentTime);

		g.dispose();
		ServletOutputStream responseOutputStream = response.getOutputStream();

		ImageIO.write(image, "JPEG", responseOutputStream);

		responseOutputStream.flush();
		responseOutputStream.close();
	}
	
	Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}