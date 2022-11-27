package View.BicycleDesigner;

import java.awt.Image;
import java.awt.Point;

public class FramesetSprite {
	private Image frameImage;
	private Point frontWheelHubLocation;
	private Point rearWheelHubLocation;
	private Point handlebarConnectLocation;
	
	public FramesetSprite(Image frameImage, Point frontWheelSpoke, Point rearWheelHubLocation, Point handlebarConnectLocation) {
		super();
		this.frameImage = frameImage;
		this.frontWheelHubLocation = frontWheelSpoke;
		this.rearWheelHubLocation = rearWheelHubLocation;
		this.handlebarConnectLocation = handlebarConnectLocation;
	}
	
	public Image getFrameImage() {
		return frameImage;
	}
	public Point getFrontWheelHubLocation() {
		return frontWheelHubLocation;
	}
	public Point getRearWheelHubLocation() {
		return rearWheelHubLocation;
	}
	public Point getHandlebarJoinLocation() {
		return handlebarConnectLocation;
	}
	
}
