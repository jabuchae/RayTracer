package raytracer;
import spatial3d.Point;

public class Camera {

	Point position;
	Point top;
	Point direction;
	double fov;
	int xresolution;
	int yresolution;


	public void place(Point position) {
		this.position = position;
	}

	public void lookAt(Point destination) {
		this.direction = destination.sub(this.position).normalize();
		
	}

	public void setTop(Point top) {
		
		Point res = top.cross(this.direction).normalize();
		
		this.top = this.direction.cross(res).normalize();
		
	}

	public void fov(double fov) {
		
		this.fov = fov;
	}

	public void setResolution(int xresolution, int yresolution) {
		
		this.xresolution = xresolution;
		this.yresolution = yresolution;
		
	}

	public int getXResolution() {
		return this.xresolution;
	}

	public int getYResolution() {
		return this.yresolution;
	}

	public Ray getRay(int u, int v) {
		
		//width of raster in the plane that is at |distance| = 1 from the camera
		double fov_width = Math.tan(this.fov / 2) * 2;
		
		double pixel_distance = fov_width / this.xresolution;
		
		
			
		Point xnormal = this.direction.cross(this.top).normalize();
		Point ynormal = this.direction.cross(xnormal).normalize();
		
		Point initial_pixel = this.position.
				add(this.direction).
				add(xnormal.scale(-1 * pixel_distance * this.xresolution / 2)).
				add(ynormal.scale(-1 * pixel_distance * this.yresolution / 2));
	
		
		Point ray_point = initial_pixel.
			add(xnormal.scale(pixel_distance * (u+0.5))).
			add(ynormal.scale(pixel_distance * (v+0.5)));
		
		
		return new Ray(this.position, ray_point.sub(this.position));
		
	}
	
	
}
