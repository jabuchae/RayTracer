package spatial3d.shape;

import raytracer.Ray;
import raytracer.image.Colour;
import spatial3d.Intersection;
import spatial3d.Point;

public class Box implements Shape {

	private Point min;
	private Point max;
	private Colour colour;
	
	public Box(Point min, Point max, Colour colour) {
		this.min = min;
		this.max = max;
		this.colour = colour;
	}
	
	@Override
	public Colour getColour(Point point) {
		return this.colour;
	}

	@Override
	public Intersection intersect(Ray ray) {

		InOut i = new InOut();
				
		// For each pair of plane aligned with X, Y and Z
		boolean i_x = this.getIntersection(i, 
			ray.getOrigin().getX(),
			ray.getDirection().getX(),
			this.min.getX(),
			this.max.getX()
		);
		if(!i_x){
			return null;
		}
		
		boolean i_y = this.getIntersection(i, 
			ray.getOrigin().getY(),
			ray.getDirection().getY(),
			this.min.getY(),
			this.max.getY()
		);
		
		if(!i_y){
			return null;
		}
		
		boolean i_z = this.getIntersection(i, 
			ray.getOrigin().getZ(),
			ray.getDirection().getZ(),
			this.min.getZ(),
			this.max.getZ()
		);
		
		if(!i_z){
			return null;
		}
		
		return new Intersection(this, ray.getOrigin().add(ray.getDirection().scale(i.near)));
	}

	@Override
	public Point getNormal(Point point) {
		Point normal = new Point(0,0,0);
		
		if(Math.abs(point.getX() - this.min.getX()) < 0.0005){
			normal = normal.add(new Point(-1,0,0));
		}
		
		if(Math.abs(point.getX() - this.max.getX()) < 0.0005){
			normal = normal.add(new Point(1,0,0));
		}
		
		if(Math.abs(point.getY() - this.min.getY()) < 0.0005){
			normal = normal.add(new Point(0,-1,0));
		}
		
		if(Math.abs(point.getY() - this.max.getY()) < 0.0005){
			normal = normal.add(new Point(0,1,0));
		}
		
		if(Math.abs(point.getZ() - this.min.getZ()) < 0.0005){
			normal = normal.add(new Point(0,0,-1));
		}
		
		if(Math.abs(point.getZ() - this.max.getZ()) < 0.0005){
			normal = normal.add(new Point(0,0,1));
		}
		
		return normal.normalize();
	}

	private boolean getIntersection(InOut i, double ro, double rd, double min, double max){

		if(rd == 0){
			
			if(ro < min || ro > max){
				return false;
			}
		}
		
		double T1 = (min - ro) / rd;
		double T2 = (max - ro) / rd;
		
		if(T1 > T2){
			double aux = T1;
			T1 = T2;
			T2 = aux;
		}
		
		if(T1 > i.near){
			i.near = T1;
		}
		
		if(T2 < i.far){
			i.far = T2;
		}
		
		if(i.near > i.far || i.far < 0){
			return false;
		}
		
		return true;
	}
}

class InOut{
	protected double near;
	protected double far;
	
	public InOut() {
		this.near = Double.NEGATIVE_INFINITY;
		this.far = Double.POSITIVE_INFINITY;
	}
}
