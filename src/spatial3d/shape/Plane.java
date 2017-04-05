package spatial3d.shape;

import raytracer.Ray;
import raytracer.image.Colour;
import spatial3d.Intersection;
import spatial3d.Point;

public class Plane implements Shape{
	private Point apoint;
	private Point normal;
	Colour colour;
	
	public Plane(Point apoint, Point normal, Colour colour) {
		this.apoint = apoint;
		this.normal = normal.normalize();
		this.colour = colour;
	}
	
	@Override
	public Colour getColour(Point point) {
		return this.colour;
	}

	@Override
	public Intersection intersect(Ray ray) {
		Point l = ray.getDirection();
		Point n = this.normal;
		
		double ln = l.dot(n);
		
		
		if(ln == 0 && ray.getOrigin().sub(this.apoint).dot(n) == 0){
			
			return null;
			
		}
		
		Point intersection_point = ray.getOrigin();
		
		if(ln != 0){
			double d = this.apoint.sub(intersection_point).dot(n)/ln;
			
			if(d <= 0){
				return null;
			}
			
			intersection_point = l.scale(d).add(ray.getOrigin());
		}
		
		return new Intersection(this, intersection_point);
		
	}

	@Override
	public Point getNormal(Point point) {
		return this.normal;
	}

	@Override
	public String toString() {
		return "Plane: aPoint" + this.apoint+ " Normal" + this.normal;
	}
}
