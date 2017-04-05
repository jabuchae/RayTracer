package spatial3d;

public class Point {

	private double x, y, z;
	
	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Point sub(Point point) {
		return new Point(this.x - point.x, this.y - point.y, this.z - point.z);
	}
	
	public Point add(Point point){
		return new Point(this.x + point.x, this.y + point.y, this.z + point.z);
	}

	public Point normalize() {
		double length = this.getLength();
		
		return new Point(this.x/length, this.y/length, this.z/length);
		
	}

	public double getLength() {
		return Math.sqrt(this.dot(this));
	}

	public Point scale(double l) {
		return new Point(this.x * l, this.y * l, this.z * l);
	}

	public Point cross(Point p) {
		Point crossp = new Point(
			this.y * p.z - this.z * p.y,
			this.z * p.x - this.x * p.z,
			this.x * p.y - this.y * p.x
		);
		
		return crossp;
	}

	public double projectionLength(Point p) {
		
		return this.dot(p) / p.getLength();
	}

	public double dot(Point p) {
		
		return this.x * p.x + this.y * p.y + this.z * p.z;
	}
	
	@Override
	public String toString() {
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}
	
}
 