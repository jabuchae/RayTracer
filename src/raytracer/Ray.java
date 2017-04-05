package raytracer;
import spatial3d.Intersection;
import spatial3d.Point;
import spatial3d.shape.Shape;

public class Ray {
	private Point origin;
	private Point direction;
	
	public Ray(Point origin, Point direction) {
		this.origin = origin;
		this.direction = direction.normalize();
	}

	public Point getDirection() {
		return this.direction;
	}
	
	public Point getOrigin(){
		return this.origin;
	}

	public Intersection intersect(Shape shape) {
		return shape.intersect(this);
	}
	
	@Override
	public String toString() {
		return "Origin: " + this.origin + " Direction: " + this.direction;

	}

	
}
