package spatial3d.shape;

import raytracer.Ray;
import raytracer.image.Colour;
import spatial3d.Intersection;
import spatial3d.Point;

public interface Shape {

	Colour getColour(Point point);

	Intersection intersect(Ray ray);

	Point getNormal(Point point);

}
