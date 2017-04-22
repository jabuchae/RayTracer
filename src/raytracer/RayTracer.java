package raytracer;

import java.util.Collection;

import raytracer.image.Colour;
import spatial3d.Intersection;
import spatial3d.light.Light;
import spatial3d.light.Luminescence;
import spatial3d.shape.Shape;
import raytracer.image.Image;

public class RayTracer {

		private Scene scene;
		private Camera camera;
		private int smooth;
		
		public RayTracer(Scene scene, Camera camera) {
		
			this.scene = scene;
			this.camera = camera;
			
			this.smooth = 1;
			
		}
		
		public void setSmooth(int smooth){
			this.smooth = smooth;
		}

		public Image buildImage() {
			
			int xresolution = this.camera.getXResolution()*this.smooth;
			int yresolution = this.camera.getYResolution()*this.smooth;

			this.camera.setResolution(xresolution, yresolution);
			
			
			Image image = new Image(xresolution, yresolution);
			
			for (int u = 0; u < xresolution; u++) {
			
				for (int v = 0; v < yresolution; v++) {
					Colour colour = this.getPixelColour(u, v);

					image.setPixelColour(u, v, colour);
					
				}
			}
			
			image.smooth(this.smooth);
			
			return image;
		
		}

		private Colour getPixelColour(int u, int v) {
			Ray ray = camera.getRay(u, v);
			Intersection intersection = this.scene.getFirtIntersection(ray);
			
			
			if(intersection == null){
				return Colour.BLACK;
			}
			
			
			Shape intersected_shape = intersection.getShape();
			
			Colour colour = intersected_shape.getColour(intersection.getPoint());
			
			Collection<Light> lights = scene.getLights();
						
			Luminescence total_lum = null;
			
			for (Light light : lights) {
				
				Luminescence lum = light.getLuminescence(this.scene, intersection.getPoint(), intersection.getNormal());
				
				if(lum != null){
					total_lum = lum.add(total_lum);
				}
				
			}
			
			
			if(total_lum == null || total_lum.getBrightness() == 0){
				return Colour.BLACK;
			}
			
			return total_lum.reflect(colour).getColour();
		}
}






