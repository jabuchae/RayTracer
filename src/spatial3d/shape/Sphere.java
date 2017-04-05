package spatial3d.shape;

import raytracer.Ray;
import raytracer.image.Colour;
import spatial3d.Intersection;
import spatial3d.Point;

public class Sphere implements Shape{

	private Point center;
	private double radius;
	Colour colour;
	
	public Sphere(Point center, double radius, Colour colour) {
		this.center = center;
		this.radius = radius;
		this.colour = colour;
	}
	
	@Override
	public Colour getColour(Point point) {
		return this.colour;
	}
	
	public Point getCenter(){
		return center;
	}
	
	public double getRadius(){
		return radius;
	}

	@Override
	public Intersection intersect(Ray ray) {
		Point l = ray.getDirection().normalize();
		Point o = ray.getOrigin();
		Point c = this.center;
		double r = this.radius;
		
		double b = l.dot(o.sub(c));
		double oc_distance = o.sub(c).getLength();
		double root = (b*b) - (oc_distance*oc_distance) + (r*r);
		
		if(root < 0){
			return null;
		}
		
		double d = -1 * b;
		
		if(root > 0){
			root = Math.sqrt(root);
			
			if(d - root  < 0){
				d = d + root;
			} else {
				d = d - root;
			}
		}
		
		if(d < 0){
			return null;
		}
		
		
		return new Intersection(this, o.add(l.scale(d)));
		
	}

	@Override
	public Point getNormal(Point point) {
		return point.sub(this.center).normalize();
	}
	
	@Override
	public String toString() {
		return "Sphere: Center " + this.center + " Radius " + this.radius;
	}
}
