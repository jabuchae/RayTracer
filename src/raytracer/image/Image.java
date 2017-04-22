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

	public void smooth(int smooth) {

		int xresolution = this.image.getWidth()/smooth;
		int yresolution = this.image.getHeight()/smooth;
		
		// You've been hit by... you've been struck by...
		BufferedImage smooth_image = new BufferedImage(xresolution, yresolution, BufferedImage.TYPE_INT_RGB);

		for(int i = 0; i < xresolution; i++){
			for(int j = 0; j < yresolution; j++){
				Colour smoother_color = null;
				
				for( int u = 0; u < smooth; u++){
					for( int v = 0; v < smooth; v++){
						Colour c = new Colour(this.image.getRGB(i*smooth + u, j*smooth + v));
						smoother_color  = c.add(smoother_color);
					}
				}
				
				smooth_image.setRGB(i, j, smoother_color.getRGB());
			}
		}
		
		
		this.image = smooth_image;
		
		return ;
	}

}
