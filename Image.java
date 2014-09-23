package edu.upenn.cis573.hwk2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

public class Image {
	private Bitmap image;
	private Point imagePoint;
	
	public Image(int x, int y) {
		imagePoint = new Point(x, y);
	}
	
	protected void setAndScaleImage(Resources res, int id, 
			int dstWidth, int dstHeight, boolean filter) {
		image = BitmapFactory.decodeResource(res, id);
		image = Bitmap.createScaledBitmap(image, dstWidth, dstHeight, filter);
	}
	
	protected void setImagePoint(int x, int y) {
		imagePoint = new Point(x, y);
	}
	
	protected void offsetImagePoint(int dx, int dy) {
		imagePoint.offset(dx, dy);
	}
	
	protected Point getImagePoint() {
		return imagePoint;
	}
	
	protected Bitmap getImage() {
		return image;
	}

}
