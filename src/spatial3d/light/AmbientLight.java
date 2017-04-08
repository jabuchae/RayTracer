package spatial3d.light;

import raytracer.Scene;
import raytracer.image.Colour;
import spatial3d.Point;

public class AmbientLight implements Light {
	
	private Colour colour;
	private double brightness;
	
	
	public AmbientLight(Colour colour, double brightness) {
		this.colour = colour;
		this.brightness = brightness;
	}
	
	@Override
	public Luminescence getLuminescence(Scene scene, Point p, Point normal) {
		return new Luminescence(this.colour, this.brightness);
	}

}
