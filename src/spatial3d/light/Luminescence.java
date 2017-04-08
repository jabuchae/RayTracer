package spatial3d.light;


import raytracer.image.Colour;

public class Luminescence {
	private double brightness;
	private Colour colour;
	
	public Luminescence(Colour colour, double brightness) {
		this.colour = colour;
		this.brightness = brightness;
	}
	
	public double getBrightness(){
		return this.brightness;
	}
	
	public Colour getColour(){
		return this.colour.brighten(this.brightness);
	}

	public Luminescence add(Luminescence lum) {
		if(lum == null){
			return new Luminescence(this.colour, this.brightness);
		}
		
		return new Luminescence(this.getColour().add(lum.getColour()), this.getBrightness() + lum.getBrightness());
	}

	public Luminescence reflect(Colour sufraceColour) {
		Colour c = new Colour(
			Math.min(sufraceColour.getRed(), this.colour.getRed()),
			Math.min(sufraceColour.getGreen(), this.colour.getGreen()),
			Math.min(sufraceColour.getBlue(), this.colour.getBlue())
		);
		
		return new Luminescence(c, this.brightness);
	}
}
