import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageClass {
	public static final String ext = "jpg";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Matrix pixels = ImageClass.readImage("lena.jpg");

		ImageClass.writeImage(pixels, ".jpg");

	}

	public static Matrix readImage(String path) {

		BufferedImage img;
		try {
			img = ImageIO.read(new File(path));

			int hieght = img.getHeight();
			int width = img.getWidth();

			Matrix imagePixels = new Matrix(hieght, width);
			for (int y = 0; y < hieght; y++) {
				for (int x = 0; x < width; x++) {

					int pixel = img.getRGB(x, y);

					int red = (pixel & 0x00ff0000) >> 16;
					// int grean=(pixel & 0x0000ff00) >> 8;
					// int blue=pixel & 0x000000ff;
					// int alpha=(pixel & 0xff000000) >> 24;
					imagePixels.set(y, x, red);
				}
			}

			return imagePixels;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}

	}

	public static void writeImage(Matrix imagePixels, String outPath) {

		BufferedImage image = new BufferedImage(imagePixels.getCol(),
				imagePixels.getRow(), BufferedImage.TYPE_INT_RGB);
		for (int y = 0; y < imagePixels.getRow(); y++) {
			for (int x = 0; x < imagePixels.getCol(); x++) {
				if(imagePixels.get(y, x)<0)
					imagePixels.set(y, x, 0);
				if(imagePixels.get(y,x)>255)
					imagePixels.set(y,x,255);
				int value = -1 << 24;
				value = 0xff000000 | (imagePixels.get(y, x) << 16)
						| (imagePixels.get(y, x) << 8)
						| (imagePixels.get(y, x));
				image.setRGB(x, y, value);

			}
		}

		File ImageFile = new File(outPath);
		try {
			ImageIO.write(image, "jpg", ImageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
