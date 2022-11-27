package View;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceSingleton {
	public static Image getSaveIcon() {
		final String saveIconLocation = "../Resources/save_very_small.png";
		Image img = null;
		try {
			img = ImageIO.read(ResourceSingleton.class.getResource(saveIconLocation));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
}
