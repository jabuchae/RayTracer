package spatial3d.shape;

import java.util.ArrayList;
import java.util.List;

import raytracer.Ray;
import raytracer.image.Colour;
import spatial3d.Intersection;
import spatial3d.Point;

public class Mesh implements Shape {

	private List<Triangle> triangles;
	private Box aabb;
	
	public Mesh() {
		this.aabb = null;
		this.triangles = new ArrayList<>();
	}
	
	public void addTriangle(Triangle t){
		triangles.add(t);
		
		double min_x = Math.min(t.v3.getX(), Math.min(t.v1.getX(), t.v2.getX()) );
		double min_y = Math.min(t.v3.getY(), Math.min(t.v1.getY(), t.v2.getY()) );
		double min_z = Math.min(t.v3.getZ(), Math.min(t.v1.getZ(), t.v2.getZ()) );
		
		double max_x = Math.max(t.v3.getX(), Math.min(t.v1.getX(), t.v2.getX()) );
		double max_y = Math.max(t.v3.getY(), Math.min(t.v1.getY(), t.v2.getY()) );
		double max_z = Math.max(t.v3.getZ(), Math.min(t.v1.getZ(), t.v2.getZ()) );
		
		if(this.aabb != null){
			min_x = Math.min(this.aabb.min.getX(), min_x );
			min_y = Math.min(this.aabb.min.getY(), min_y );
			min_z = Math.min(this.aabb.min.getZ(), min_z );
			
			max_x = Math.max(this.aabb.max.getX(), max_x );
			max_y = Math.max(this.aabb.max.getY(), max_y );
			max_z = Math.max(this.aabb.max.getZ(), max_z );
		}
		
		
		this.aabb = new Box(new Point(min_x, min_y, min_z), new Point(max_x, max_y, max_z), new Colour(0,0,0));
	}
	
	@Override
	public Colour getColour(Point point) {
		return null;
	}

	@Override
	public Intersection intersect(Ray ray) {
		
		//Check intersection with bounding box
		if(ray.intersect(this.aabb) == null){
			return null;
		}
		
		Intersection intersection = null;
		for (Triangle triangle : this.triangles) {
			Intersection tmp_intersection = ray.intersect(triangle);

			if(tmp_intersection != null && tmp_intersection.before(ray, intersection)){
				intersection = tmp_intersection;
			} 
			
		}

		return intersection;
	}

	@Override
	public Point getNormal(Point point) {
		return null;
	}

}
