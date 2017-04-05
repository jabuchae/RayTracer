package spatial3d.light;


import raytracer.Scene;
import raytracer.image.Colour;
import spatial3d.Point;

public class OmniLight implements Light{

	private Point location;
	private Colour colour;
	private double brightness;
	
	public OmniLight(Point location, Colour colour, double brightness) {
		this.location = location;
		this.colour = colour;
		this.brightness = brightness;
	}

	@Override
	public Luminescence getLuminescence(Scene scene, Point p, Point normal) {

		boolean visible = scene.areVisible(p, this.location);
		
		double brightness = ((visible ? 1 : 0) * this.brightness);
		
		brightness = brightness * Math.abs(p.sub(this.location).normalize().dot(normal));
		
		return new Luminescence(this.colour, brightness);
	}
}
