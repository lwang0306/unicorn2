package edu.upenn.cis573.hwk2;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

/**
 * This class holds the image to display, and its position.
 */
public class Image {
	private Bitmap image;
	private Point imagePoint;

	/**
	 * Construct an Image by position (x, y).
	 * 
	 * @param x
	 * @param y
	 */
	public Image(int x, int y) {
		imagePoint = new Point(x, y);
	}

	/**
	 * Set and scale an bitmap.
	 * 
	 * @param res
	 *            The resources object containing the image data.
	 * @param id
	 *            The resources id of the image data.
	 * @param dstWidth
	 *            The new bitmap's desired width.
	 * @param dstHeight
	 *            The new bitmap's desired height.
	 * @param filter
	 *            true if the source should be filtered
	 */
	protected void setAndScaleImage(Resources res, int id, int dstWidth,
			int dstHeight, boolean filter) {
		image = BitmapFactory.decodeResource(res, id);
		image = Bitmap.createScaledBitmap(image, dstWidth, dstHeight, filter);
	}

	/**
	 * Set the image position to (x, y)
	 * 
	 * @param x
	 * @param y
	 */
	protected void setImagePoint(int x, int y) {
		imagePoint = new Point(x, y);
	}

	/**
	 * Off set the image position by (dx, dy)
	 * 
	 * @param dx
	 * @param dy
	 */
	protected void offsetImagePoint(int dx, int dy) {
		imagePoint.offset(dx, dy);
	}

	/**
	 * Get the position of image.
	 * 
	 * @return Point of position
	 */
	protected Point getImagePoint() {
		return imagePoint;
	}

	/**
	 * Get the image data
	 * 
	 * @return image data
	 */
	protected Bitmap getImage() {
		return image;
	}

	/**
	 * Check if current position is within bound.
	 * 
	 * @param x
	 *            boundary of the event
	 * @param y
	 *            boundary of the event
	 * @return true if within the bound, false else
	 */
	protected boolean isWithinBound(float x, float y) {
		int width = image.getWidth();
		int height = image.getHeight();
		return (x > imagePoint.x && x < imagePoint.x + width
				&& y > imagePoint.y && y < imagePoint.y + height);
	}

}
