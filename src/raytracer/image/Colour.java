package raytracer.image;

public class Colour {
	
	public static final Colour BLACK = new Colour(0,0,0);
	
	private int red, green, blue;

	public Colour(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public int getRGB() {
		int rgb = this.red;
		rgb = (rgb << 8) + this.green;
		rgb = (rgb << 8) + this.blue;
		
		return rgb;
	}
	
	@Override
	public String toString() {
		return "RGB: (" + this.red + ", " + this.green + ", " + this.blue + ")";
	}

	public Colour brighten(double brightness) {

		return new Colour( 
			(int) (this.red * brightness), 
			(int) (this.green * brightness), 
			(int) (this.blue * brightness)
		).normalize();
	}

	public Colour add(Colour colour) {
		
		return new Colour(
			this.red + colour.red,
			this.green + colour.green,
			this.blue + colour.blue
		).normalize();
	}
	
	private Colour normalize(){
		if(this.red > 255){
			this.red = 255;
		}
		if(this.green > 255){
			this.green = 255;
		}
		if(this.blue > 255){
			this.blue = 255;
		}
		
		return this;
	}
}
