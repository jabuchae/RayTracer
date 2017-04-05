package spatial3d;

import raytracer.Ray;
import spatial3d.shape.Shape;

public class Intersection {
	
	private Shape shape;
	private Point point;
	private Point normal;

	public Intersection(Shape shape, Point point) {
		this.shape = shape;
		this.point = point;
		
		this.normal = shape.getNormal(point);
	}
	
	public Shape getShape() {
		return this.shape;
	}
	
	public Point getPoint() {
		return this.point;
	}

	public boolean before(Ray ray, Intersection intersection) {
		if(intersection == null){
			return true;
		}
		
		double self_projection = this.point.projectionLength(ray.getDirection());
		double intersection_projection = intersection.point.projectionLength(ray.getDirection());
		
		return self_projection < intersection_projection;
		
	}

	public Point getNormal() {
		return this.normal;
	}


}
