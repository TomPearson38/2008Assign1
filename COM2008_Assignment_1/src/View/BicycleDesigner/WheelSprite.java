package View.BicycleDesigner;

import java.awt.Image;
import java.awt.Point;

public class WheelSprite {
	private Image wheelImage;
	private Point wheelHubLocation;
	
	public WheelSprite(Image wheelImage, Point wheelHubLocation) {
		super();
		this.wheelImage = wheelImage;
		this.wheelHubLocation = wheelHubLocation;
	}
	
	public Image getWheelImage() {
		return wheelImage;
	}
	public Point getWheelHubLocation() {
		return wheelHubLocation;
	}
}
