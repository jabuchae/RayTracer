package raytracer.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

	private BufferedImage image;
	
	public Image(int xresolution, int yresolution) {
		this.image = new BufferedImage(xresolution, yresolution, BufferedImage.TYPE_INT_RGB);
	}
	
	public void setPixelColour(int u, int v, Colour colour) {
		
		this.image.setRGB(u, v, colour.getRGB());
		
	}
	
	public void saveImage(String path) throws IOException{
		File outputfile = new File(path);
		ImageIO.write(this.image, "png", outputfile);
	}

}
