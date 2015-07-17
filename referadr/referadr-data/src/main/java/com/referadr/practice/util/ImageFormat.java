package com.referadr.practice.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import sun.misc.BASE64Decoder;
public class ImageFormat {
	public static BufferedImage decodeToImage(String imageString) {
	    BufferedImage image = null;
	    byte[] imageByte;
	    try {
	      BASE64Decoder decoder = new BASE64Decoder();
	      imageByte = decoder.decodeBuffer(imageString);
	      ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
	      image = ImageIO.read(bis);
	      bis.close();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return image;
	  }

}
