package edu.upenn.cis573.hwk2;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class GameView extends View {
	private Image image;
	private Stroke stroke = new Stroke(Color.RED, 10);
	private boolean killed = false;
	private boolean newUnicorn = true;
	private int score = 0;
	private int yChange = 0;

	public GameView(Context context) {
		this(context, null);
	}

	public GameView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		setBackgroundResource(R.drawable.space);
		image = new Image(this);
		image.insertImage("unicorn", R.drawable.unicorn, 150, 150);
		image.insertImage("explosion", R.drawable.explosion, 150, 150);
	}

	/*
	 * This method is automatically invoked when the View is displayed. It is
	 * also called after you call "invalidate" on this object.
	 */
	protected void onDraw(Canvas canvas) {

		// resets the position of the unicorn if one is killed or reaches the
		// right edge
		if (newUnicorn || image.getImagePoint().x >= this.getWidth()) {
			image.setImagePoint(-150, (int) (Math.random() * 200 + 200));
			yChange = (int) (10 - Math.random() * 20);
			newUnicorn = false;
			killed = false;
		}

		// show the exploding image when the unicorn is killed
		if (killed) {
			image.drawBitmap(canvas, "explosion");
			newUnicorn = true;
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
			invalidate();
			return;
		}

		// draws the unicorn at the specified point
		image.drawBitmap(canvas, "unicorn");

		// draws the stroke
		stroke.drawStroke(canvas);

	}

	/*
	 * This method is automatically called when the user touches the screen.
	 */
	public boolean onTouchEvent(MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_DOWN
				|| event.getAction() == MotionEvent.ACTION_MOVE) {
			stroke.addPoint((int) event.getX(), (int) event.getY());
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			stroke.clearPoints();
		} else {
			return false;
		}

		// see if the point is within the boundary of the image
		// the !killed thing here is to prevent a "double-kill" that could occur
		// while the "explosion" image is being shown
		if (!killed && image.isWithinBound(event.getX(), event.getY())) {
			killed = true;
			score++;
			((TextView) ((Activity) getContext()).findViewById(R.id.scoreboard))
					.setText("" + score);
		}

		// forces a redraw of the View
		invalidate();

		return true;
	}

	/**
	 * Offset the image point by dx and dy.
	 * 
	 * @param dx
	 *            The offset of x coordinate.
	 * @param dy
	 *            The offset of y coordinate.
	 */
	protected void offSetImagePoint() {
		image.offsetImagePoint(10, yChange);
	}

	/**
	 * Get the current score.
	 * 
	 * @return score
	 */
	protected int getScore() {
		return score;
	}

}
