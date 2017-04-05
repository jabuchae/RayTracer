package spatial3d.light;

import raytracer.Scene;
import spatial3d.Point;

public interface Light {

	public Luminescence getLuminescence(Scene scene, Point p, Point normal);
}
