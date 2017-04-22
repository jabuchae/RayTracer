import java.io.IOException;

import raytracer.Camera;
import raytracer.RayTracer;
import raytracer.Scene;
import raytracer.image.Colour;
import raytracer.image.Image;
import spatial3d.Point;
import spatial3d.light.AmbientLight;
import spatial3d.light.Light;
import spatial3d.light.OmniLight;
import spatial3d.shape.Box;
import spatial3d.shape.Mesh;
import spatial3d.shape.Plane;
import spatial3d.shape.Shape;
import spatial3d.shape.Sphere;
import spatial3d.shape.Triangle;

public class TracerMain {

		public static void main(String[] args) {
			System.out.println("Empezando el tracing");
			
			
			RayTracer tracer;
			Scene scene = new Scene();
			Camera camera = new Camera();
			
			buildScene(scene);
			
			placeCamera(camera);
			
			camera.setResolution(800, 600);
			
			tracer = new RayTracer(scene, camera);
			
			tracer.setSmooth(2);
			
			Image image = tracer.buildImage();
			
			String path = "sampleimage.png";
			
			try {
				image.saveImage(path);

				System.out.println("Imaged saved on path: " + path);
			} catch (IOException e) {
				System.out.println("Error saving the image to path: " + path);
			}
			
			
		}

		private static void placeCamera(Camera camera) {
			camera.place(new Point(10,10,5));
			camera.lookAt(new Point(0,0,0));
			camera.setTop(new Point(0,0,1));
			camera.fov(Math.PI/4);
			
		}

		private static void buildScene(Scene scene) {
			
			Shape sulzy;
			
			sulzy = new Sphere(new Point(2,-2,0), 1, new Colour(100,100,20));
			scene.addShape(sulzy);
			
			Shape plane;
			plane = new Plane(new Point(-5,0,0), new Point(1,0,0), new Colour(20, 20, 200));
			scene.addShape(plane);
			

			Shape plane2;
			plane2 = new Plane(new Point(0,0,-1), new Point(0,0,1), new Colour(100, 20, 20));
			scene.addShape(plane2);
			
			
			Shape box;
			box = new Box(new Point(0,0,-1), new Point(2,2,1), new Colour(255, 90, 0));
			scene.addShape(box);
			
			
			Light light = new OmniLight(new Point(10,-8,10), new Colour(255,255,255), 0.8);
			scene.addLight(light);
		
			Light ambientLight = new AmbientLight(new Colour(255,255,255), 0.7);
			scene.addLight(ambientLight);
			
			Mesh mesh = new Mesh();
			Colour mesh_colour = new Colour(200,200,50);
			mesh.addTriangle(
				new Triangle(
					new Point(0,0,1), 
					new Point(1,1,1), 
					new Point(0,1,1), 
					mesh_colour
				)
			);
			mesh.addTriangle(
					new Triangle(
						new Point(2,2,1), 
						new Point(1,1,1), 
						new Point(2,3,1), 
						mesh_colour
					)
				);
			mesh.addTriangle(
					new Triangle(
						new Point(0,0,1), 
						new Point(1,1,3), 
						new Point(0,1,3), 
						mesh_colour
					)
				);
			scene.addShape(mesh);
			
			return;
		}
}
