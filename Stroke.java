package edu.upenn.cis573.hwk2;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

/**
 * This class contains the trail, color and width of user's actions.
 */
public class Stroke {

	private ArrayList<Point> points;
	private int lineColor;
	private int lineWidth;

	public Stroke(int color, int width) {
		points = new ArrayList<Point>();
		lineColor = color;
		lineWidth = width;
	}

	/**
	 * Get the trail of user's action.
	 * 
	 * @return ArrayList of Point.
	 */
	protected ArrayList<Point> getPoints() {
		return points;
	}

	/**
	 * Get the line color.
	 * 
	 * @return lineColor
	 */
	protected int getColor() {
		return lineColor;
	}

	/**
	 * Get the line width.
	 * 
	 * @return lineWidth
	 */
	protected int getWidth() {
		return lineWidth;
	}

	/**
	 * Clear all Points in the trail.
	 */
	protected void clearPoints() {
		points.clear();
	}

	/**
	 * Add a new Point to the trail.
	 * 
	 * @param x
	 * @param y
	 */
	protected void addPoint(int x, int y) {
		points.add(new Point(x, y));
	}

	/**
	 * Draw the stroke.
	 * 
	 * @param canvas
	 *            The canvas to draw on.
	 */
	protected void drawStroke(Canvas canvas) {
		if (getPoints().size() > 1) {
			for (int i = 0; i < getPoints().size() - 1; i++) {
				int startX = getPoints().get(i).x;
				int stopX = getPoints().get(i + 1).x;
				int startY = getPoints().get(i).y;
				int stopY = getPoints().get(i + 1).y;
				Paint paint = new Paint();
				paint.setColor(getColor());
				paint.setStrokeWidth(getWidth());
				canvas.drawLine(startX, startY, stopX, stopY, paint);
			}
		}
	}

}
