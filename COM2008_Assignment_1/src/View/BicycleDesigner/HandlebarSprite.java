package View.BicycleDesigner;

import java.awt.Image;
import java.awt.Point;

public class HandlebarSprite {
	private Image handlebarImage;
	private Point handlebarConnectionLocation;
	public HandlebarSprite(Image handlebarImage, Point handlebarConnectionLocation) {
		super();
		this.handlebarImage = handlebarImage;
		this.handlebarConnectionLocation = handlebarConnectionLocation;
	}
	public Image getHandlebarImage() {
		return handlebarImage;
	}
	public Point getHandlebarJoinLocation() {
		return handlebarConnectionLocation;
	}
	
}
