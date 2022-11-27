package View.BicycleDesigner;

import java.awt.Point;

import View.ResourceSingleton;

public class DefaultSprites {
	public static FramesetSprite getDefaultFramesetSprite() {
		return new FramesetSprite(ResourceSingleton.getDefaultFrameImage(), new Point(390, 178), new Point(47,175), new Point(339, 26));
	}
	
	public static WheelSprite getDefaultWheelSprite() {
		return new WheelSprite(ResourceSingleton.getDefaultWheelImage(), new Point(100,100));
	}
	
	public static HandlebarSprite getDefaultHandlebarSprite() {
		return new HandlebarSprite(ResourceSingleton.getDefaultHandlebarsImage(), new Point(7, 7));
	}
}
