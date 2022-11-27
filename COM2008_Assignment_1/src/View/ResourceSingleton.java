package View;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ResourceSingleton {
	static final String resourcesFolderLocation = "../Resources/";
	static final String saveIconLocation = resourcesFolderLocation + "save_very_small.png";
	static final String defaultWheelImageLocation = resourcesFolderLocation + "default_wheel.png";
	static final String defaultHandlebarsImageLocation = resourcesFolderLocation + "default_handlebar.png";
	static final String defaultFrameImageLocation = resourcesFolderLocation + "default_frame.png";
	
	private static Image parseImageFromFile(String fileLocation) {
		Image img = null;
		try {
			img = ImageIO.read(ResourceSingleton.class.getResource(fileLocation));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
	
	public static Image getSaveIcon() {
		return parseImageFromFile(saveIconLocation);
	}
	
	public static Image getDefaultWheelImage() {
		return parseImageFromFile(defaultWheelImageLocation);
	}
	
	public static Image getDefaultHandlebarsImage() {
		return parseImageFromFile(defaultHandlebarsImageLocation);
	}

	public static Image getDefaultFrameImage() {
		return parseImageFromFile(defaultFrameImageLocation);
	}
}
