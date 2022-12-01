package View.BicycleDesigner;

import java.awt.Point;

import Resources.ResourceSingleton;

public class DefaultSprites {
	public static FramesetSprite getDefaultFramesetSprite() {
		return new FramesetSprite(ResourceSingleton.getDefaultFrameImage(), new Point(390, 178), new Point(47,175), new Point(339, 26));
	}
	
	public static WheelSprite getRoadWheelSprite() {
		return new WheelSprite(ResourceSingleton.getDefaultWheelImage(), new Point(100,100));
	}
	
	public static WheelSprite getHyrbidWheelSprite() {
		return new WheelSprite(ResourceSingleton.getHybridWheelImage(), new Point(100,100));
	}
	
	public static WheelSprite getOffroadWheelSprite() {
		return new WheelSprite(ResourceSingleton.getOffroadWheelImage(), new Point(100,100));
	}
	
	public static HandlebarSprite getDefaultHandlebarsSprite() {
		return new HandlebarSprite(ResourceSingleton.getDefaultHandlebarsImage(), new Point(5, 22));
	}
	
	public static HandlebarSprite getHighHandlebarsSprite() {
		return new HandlebarSprite(ResourceSingleton.getHighHandlebarsImage(), new Point(5,22));
	}
	
	public static HandlebarSprite getDroppedHandlebarsSprite() {
		return new HandlebarSprite(ResourceSingleton.getDroppedHandlebarsImage(), new Point(7,7));
	}
}
