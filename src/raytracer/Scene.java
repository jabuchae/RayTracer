package raytracer;

import java.util.ArrayList;
import java.util.Collection;

import spatial3d.Intersection;
import spatial3d.Point;
import spatial3d.light.Light;
import spatial3d.shape.Shape;

public class Scene {
	
	Collection<Shape> shapes;
	Collection<Light> lights;
	
	public Scene() {
		this.shapes = new ArrayList<Shape>();
		this.lights = new ArrayList<Light>();
	}

	public Intersection getFirtIntersection(Ray ray) {
		
		Intersection intersection = null;
		for (Shape shape : shapes) {
			Intersection tmp_intersection = ray.intersect(shape);

			if(tmp_intersection != null && tmp_intersection.before(ray, intersection)){
				intersection = tmp_intersection;
			} 
			
		}

		return intersection;
	}

	public void addShape(Shape shape) {
		this.shapes.add(shape);
	}
	
	public void addLight(Light light) {
		this.lights.add(light);
	}

	public Collection<Light> getLights() {
		return this.lights;
	}

	public boolean areVisible(Point p1, Point p2) {
		
		Point direction = p2.sub(p1).normalize();
		
		Ray ray = new Ray(p1.add(direction.scale(0.001)), direction);
		
		Intersection intersection = this.getFirtIntersection(ray);
		
		if(intersection == null){
			return true;
		}
		
		Point intersection_point = intersection.getPoint();
		
		double l1 = p2.sub(p1).getLength();
		double l2 = intersection_point.sub(p1).getLength();
		
		return l1<=l2;
	}
}
