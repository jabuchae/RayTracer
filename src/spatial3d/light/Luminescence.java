package spatial3d.light;

import java.util.logging.Level;
import java.util.logging.Logger;

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
}
