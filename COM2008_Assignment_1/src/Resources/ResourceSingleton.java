package Resources;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * Loads required resources from the saved image
 * @author Alex Dobson
 *
 */
public class ResourceSingleton {
	static final String resourcesFolderLocation = "../Resources/";
	static final String taskbarIcon = resourcesFolderLocation + "icon.png";
	static final String saveIconLocation = resourcesFolderLocation + "save_very_small.png";
	static final String defaultWheelImageLocation = resourcesFolderLocation + "default_wheel.png";
	static final String hybridWheelImageLocation = resourcesFolderLocation + "hybrid_wheel.png";
	static final String offroadWheelImageLocation = resourcesFolderLocation + "offroad_wheel.png";
	static final String defaultHandlebarsImageLocation = resourcesFolderLocation + "default_handlebar.png";
	static final String highHandlebarsImageLocation = resourcesFolderLocation + "high_handlebar.png";
	static final String droppedHandlebarsImageLocation = resourcesFolderLocation + "dropped_handlebar.png";
	static final String defaultFrameImageLocation = resourcesFolderLocation + "default_frame.png";
	static final String blueprintImageLocation = resourcesFolderLocation + "blueprint.jpg";
	static final String loadingGifLocation = resourcesFolderLocation + "loading_128.gif";
	static final String shoppingCartIconLocation = resourcesFolderLocation + "shoppingCartIcon.png";

	
	/**
	 * Loads image based on supplied path
	 * @param fileLocation Path
	 * @return Image
	 */
	private static Image parseImageFromFile(String fileLocation) {
		Image img = null;
		try {
			img = ImageIO.read(ResourceSingleton.class.getResource(fileLocation));
			
		} catch (IOException e) {
			System.out.println(fileLocation);
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
	
	public static Image getHybridWheelImage() {
		return parseImageFromFile(hybridWheelImageLocation);
	}
	
	public static Image getOffroadWheelImage() {
		return parseImageFromFile(offroadWheelImageLocation);
	}
	
	public static Image getDefaultHandlebarsImage() {
		return parseImageFromFile(defaultHandlebarsImageLocation);
	}
	
	public static Image getHighHandlebarsImage() {
		return parseImageFromFile(highHandlebarsImageLocation);
	}
	
	public static Image getDroppedHandlebarsImage() {
		return parseImageFromFile(droppedHandlebarsImageLocation);
	}

	public static Image getDefaultFrameImage() {
		return parseImageFromFile(defaultFrameImageLocation);
	}
	
	public static Image getBlueprintImage() {
		return parseImageFromFile(blueprintImageLocation);
	}
	
	public static Image getShoppingImage() {
		return parseImageFromFile(shoppingCartIconLocation);
	}
	
	public static Image getTaskbarIcon() {
		return parseImageFromFile(taskbarIcon);
	}
	
	public static URL getLoadingImage() {
		return ResourceSingleton.class.getResource(loadingGifLocation);
	}
}
