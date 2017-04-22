package spatial3d.shape;

import raytracer.Ray;
import raytracer.image.Colour;
import spatial3d.Intersection;
import spatial3d.Point;

public class Triangle implements Shape {

	private Colour colour;
	protected Point v1, v2, v3;
	private Point normal;
	
	public Triangle(Point v1, Point v2, Point v3, Colour colour) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
		
		this.normal = v2.sub(v1).cross(v3.sub(v1)).normalize();
		
		this.colour = colour;
	}
	
	@Override
	public Colour getColour(Point point) {
		return this.colour;
	}

	@Override
	public Intersection intersect(Ray ray) {
		Point e1, e2;
		Point d = ray.getDirection();
		Point o = ray.getOrigin();
		
		e1 = this.v2.sub(this.v1);
		e2 = this.v3.sub(this.v1);
		
		Point p = d.cross(e2);
		double det = e1.dot(p);
		
		if(det == 0){
			return null;
		}
		
		double inv_det = 1/det;
		
		Point t = o.sub(this.v1);
		
		double u = t.dot(p) * inv_det;
		
		if(u < 0 || u > 1){
			return null;
		}
		
		Point q = t.cross(e1);

		double v = d.dot(q) * inv_det;

		if(v < 0 || u + v > 1){
			return null;
		}

		double tr = e2.dot(q) * inv_det;
		
		if(tr < 0.0005){
			return null;
		}

		return new Intersection(this, o.add(d.scale(tr)));
	}

	@Override
	public Point getNormal(Point point) {
		return this.normal;
	}

}
