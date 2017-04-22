package raytracer.image;

public class Colour {
	
	public static final Colour BLACK = new Colour(0,0,0);
	
	private int red, green, blue;

	public Colour(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
		
	}
	
	public Colour(int argb) {

		this.red = 0xFF & ( argb >> 16);
		this.green = 0xFF & (argb >> 8 );
		this.blue = 0xFF & (argb >> 0 );
		
	}

	public int getRGB() {
		return (this.red << 16 ) | (this.green<<8) | this.blue;
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
		
		if(colour == null){
			return this;
		}
		
		return new Colour(
			(int) Math.sqrt((this.red*this.red + colour.red*colour.red) / 2),
			(int) Math.sqrt((this.green*this.green + colour.green*colour.green) / 2),
			(int) Math.sqrt((this.blue*this.blue + colour.blue*colour.blue) / 2)
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

	public int getRed() {
		return this.red;
	}
	
	public int getGreen() {
		return this.green;
	}
	
	public int getBlue() {
		return this.blue;
	}
}
