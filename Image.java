package edu.upenn.cis573.hwk2;

import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;

/**
 * This class holds the image to display, and its position.
 */
public class Image {
	HashMap<String, Bitmap> images;
	private Point imagePoint;
	private View view;

	/**
	 * Construct an Image by position (x, y).
	 * 
	 * @param x
	 * @param y
	 */
	public Image(View gv) {
		view = gv;
		imagePoint = new Point(-150, 100);
		images = new HashMap<String, Bitmap>();
	}

	/**
	 * Add bitmap to Image with a key.
	 * 
	 * @param key
	 *            A string key of the bitmap.
	 * @param id
	 *            The id of the image.
	 * @param dstWidth
	 *            The new bitmap's desired width.
	 * @param dstHeight
	 *            The new bitmap's desired height.
	 */
	protected void insertImage(String key, int id, int dstWidth, int dstHeight) {
		Bitmap bitmap = BitmapFactory.decodeResource(view.getResources(), id);
		bitmap = Bitmap.createScaledBitmap(bitmap, dstWidth, dstHeight, false);
		images.put(key, bitmap);
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
	protected Bitmap getImage(String str) {
		return images.get(str);
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
		int width = images.get("unicorn").getWidth();
		int height = images.get("unicorn").getHeight();
		return (x > imagePoint.x && x < imagePoint.x + width
				&& y > imagePoint.y && y < imagePoint.y + height);
	}

	/**
	 * draws the unicorn at the specified point
	 * 
	 * @param canvas
	 *            The canvas to draw on.
	 * @param str
	 *            The key of the bitmap to be drawn.
	 */
	protected void drawBitmap(Canvas canvas, String str) {
		canvas.drawBitmap(images.get(str), getImagePoint().x,
				getImagePoint().y, null);
	}

}
