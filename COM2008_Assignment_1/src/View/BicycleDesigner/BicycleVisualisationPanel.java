package View.BicycleDesigner;

import java.awt.Color;
import java.awt.Graphics; 
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;

import Resources.ResourceSingleton;

public class BicycleVisualisationPanel extends JPanel {
	private FramesetSprite framesetSprite = null;
	private WheelSprite wheelSprite = null;
	private HandlebarSprite handlebarSprite = null;
	
	public BicycleVisualisationPanel() {
		super();


		addComponents();
	}
	
	private void addComponents() {
		this.setBackground(Color.blue);
	}
	
	public FramesetSprite getFramesetSprite() {
		return framesetSprite;
	}

	public void setFramesetSprite(FramesetSprite framesetSprite) {
		this.framesetSprite = framesetSprite;
	}

	public WheelSprite getWheelSprite() {
		return wheelSprite;
	}

	public void setWheelSprite(WheelSprite wheelSprite) {
		this.wheelSprite = wheelSprite;
	}

	public HandlebarSprite getHandlebarSprite() {
		return handlebarSprite;
	}

	public void setHandlebarSprite(HandlebarSprite handlebarSprite) {
		this.handlebarSprite = handlebarSprite;
	}

	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        paintBlueprintBackground(g);
        
        //Frame
        FramesetSprite frameToDraw = DefaultSprites.getDefaultFramesetSprite();
        if (framesetSprite != null) {
        	frameToDraw = framesetSprite;
        }
        
        final double frameMarginPercent = 0.25;
        final double frameCanvasAreaPercent = frameMarginPercent * 2;
        final Rectangle sizeOfCanvas = g.getClipBounds();
        final Rectangle sizeOfAreaToDrawFrameOn = new Rectangle((int)(sizeOfCanvas.width * frameCanvasAreaPercent), (int)(sizeOfCanvas.height * frameCanvasAreaPercent));
        final Image originalFrameImage = frameToDraw.getFrameImage();
        final Image resizedFrameImage = frameToDraw.getFrameImage().getScaledInstance(sizeOfAreaToDrawFrameOn.width, sizeOfAreaToDrawFrameOn.height, Image.SCALE_DEFAULT);
        
        
        final Point frameTopLeft = new Point((int)(sizeOfCanvas.width * frameMarginPercent), (int)(sizeOfCanvas.height * frameMarginPercent));
        if (framesetSprite != null) {
        	g.drawImage(resizedFrameImage, frameTopLeft.x, frameTopLeft.y, this);
        }
        
        
        //Wheels
        WheelSprite wheelToDraw = DefaultSprites.getDefaultWheelSprite();
        if (wheelSprite != null) {
        	wheelToDraw = wheelSprite;
        }
        
        double frameScaleFactorHorizontal = (double)resizedFrameImage.getWidth(this) / (double)originalFrameImage.getWidth(this);
        double frameScaleFactorVertical = (double)resizedFrameImage.getHeight(this) / (double)originalFrameImage.getHeight(this);
        Point originalRearHub = frameToDraw.getRearWheelHubLocation();
        Point scaledRearHub = new Point((int)(originalRearHub.x * frameScaleFactorHorizontal), (int)(originalRearHub.y * frameScaleFactorVertical));
        
        final double wheelCanvasAreaPercent = 0.45;
        
        final Rectangle sizeOfCanvasForWheel = new Rectangle((int)(sizeOfCanvas.height * wheelCanvasAreaPercent), (int)(sizeOfCanvas.height * wheelCanvasAreaPercent));//height twice because wheel clip should be a square not a rectangle
        final Image resizedWheelImage = wheelToDraw.getWheelImage().getScaledInstance(sizeOfCanvasForWheel.width, sizeOfCanvasForWheel.height, Image.SCALE_DEFAULT);
        
        final Point rearHubLocation = new Point(frameTopLeft.x + scaledRearHub.x, frameTopLeft.y + scaledRearHub.y);
        final Rectangle resizedDimensions = new Rectangle(resizedWheelImage.getWidth(this), resizedWheelImage.getHeight(this));
        final Point rearWheelTopLeft = new Point((int)(rearHubLocation.x - resizedDimensions.width / 2.0), (int)(rearHubLocation.y - resizedDimensions.height / 2.0));
        
        if (wheelSprite != null) {
        	g.drawImage(resizedWheelImage, rearWheelTopLeft.x, rearWheelTopLeft.y, this);
        }
        
        
        
        final Point originalFrontHub = frameToDraw.getFrontWheelHubLocation();
        final Point scaledFrontHub = new Point((int)(originalFrontHub.x * frameScaleFactorHorizontal), (int)(originalFrontHub.y * frameScaleFactorVertical));
        final Point frontHubLocation = new Point(frameTopLeft.x + scaledFrontHub.x, frameTopLeft.y + scaledFrontHub.y);
        final Point frontWheelTopLeft = new Point((int)(frontHubLocation.x - resizedDimensions.width / 2.0), (int)(frontHubLocation.y - resizedDimensions.height / 2.0));
        
        if (wheelSprite != null) {
        	g.drawImage(resizedWheelImage, frontWheelTopLeft.x, frontWheelTopLeft.y, this);
        }
        
        
        
        //Handlebars
        HandlebarSprite handlebarsToDraw = DefaultSprites.getDefaultHandlebarSprite();
        if (handlebarSprite != null) {
        	handlebarsToDraw = handlebarSprite;
        }
        
        final double handlebarCanvasAreaPercent = 0.1;
        
        final Rectangle sizeOfCanvasForHandlebars = new Rectangle((int)(sizeOfCanvas.width * handlebarCanvasAreaPercent), (int)(sizeOfCanvas.height * handlebarCanvasAreaPercent));
        final Image originalHandlebarImage = handlebarsToDraw.getHandlebarImage();
        final Image resizedHandlebarImage = originalHandlebarImage.getScaledInstance(sizeOfCanvasForHandlebars.width, sizeOfCanvasForHandlebars.height, Image.SCALE_DEFAULT);
        
        
        final Point originalJoinLocationOnFrame = frameToDraw.getHandlebarJoinLocation();
        final Point scaledJoinLocationOnFrame = new Point((int)(originalJoinLocationOnFrame.x * frameScaleFactorHorizontal), (int)(originalJoinLocationOnFrame.y * frameScaleFactorVertical)); 
        final Point handlebarsJoinLocationOnFrame = new Point(frameTopLeft.x + scaledJoinLocationOnFrame.x, frameTopLeft.y + scaledJoinLocationOnFrame.y);
        
        double handlebarScaleFactorHorizontal = (double)resizedHandlebarImage.getWidth(this) / (double)originalHandlebarImage.getWidth(this);
        double handlebarScaleFactorVertical = (double)resizedHandlebarImage.getHeight(this) / (double)originalHandlebarImage.getHeight(this);
        final Point originalJoinLocationOnHandlebars = handlebarsToDraw.getHandlebarJoinLocation();
        final Point scaledJoinLocationOnHandlebars = new Point((int)(originalJoinLocationOnHandlebars.x * handlebarScaleFactorHorizontal), (int)(originalJoinLocationOnHandlebars.y * handlebarScaleFactorVertical));
        
        final Point handlebarsTopLeft = new Point((int)(handlebarsJoinLocationOnFrame.x - scaledJoinLocationOnHandlebars.x), (int)(handlebarsJoinLocationOnFrame.y - scaledJoinLocationOnHandlebars.y));
        if (handlebarSprite != null) {
        	g.drawImage(resizedHandlebarImage, handlebarsTopLeft.x, handlebarsTopLeft.y, this);
        }
        
    }
	
	private void paintBlueprintBackground(Graphics g) {
		final Image blueprintImage = ResourceSingleton.getBlueprintImage();
		final Rectangle sizeOfCanvas = g.getClipBounds();
		final Image scaledBlueprintImage = blueprintImage.getScaledInstance(sizeOfCanvas.width, sizeOfCanvas.height, Image.SCALE_DEFAULT);
		
		g.drawImage(scaledBlueprintImage, 0, 0, this);
	}
	
}
