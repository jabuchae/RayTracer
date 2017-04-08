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
import spatial3d.shape.Plane;
import spatial3d.shape.Shape;
import spatial3d.shape.Sphere;

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
			camera.place(new Point(10,0,3));
			camera.lookAt(new Point(0,0,0));
			camera.setTop(new Point(0,0,1));
			camera.fov(Math.PI/4);
			
		}

		private static void buildScene(Scene scene) {
			
			Shape sulzy;
			
			sulzy = new Sphere(new Point(2,-2,0), 1, new Colour(100,100,0));
			scene.addShape(sulzy);
			
			Shape plane;
			plane = new Plane(new Point(-5,0,0), new Point(1,0,0), new Colour(100, 0, 200));
			scene.addShape(plane);
			

			Shape plane2;
			plane2 = new Plane(new Point(0,0,-1), new Point(0,0,1), new Colour(100, 0, 0));
			scene.addShape(plane2);
			
			
			Shape box;
			box = new Box(new Point(0,0,-1), new Point(2,2,1), new Colour(0, 100, 0));
			scene.addShape(box);
			
			
			
			
			Light light = new OmniLight(new Point(20,10,10), new Colour(255,255,255), 1);
			scene.addLight(light);
		
			Light ambientLight = new AmbientLight(new Colour(255,255,255), 0.8);
			scene.addLight(ambientLight);
			
			return;
		}
}
