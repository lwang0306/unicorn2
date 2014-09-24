package edu.upenn.cis573.hwk2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


public class GameView extends View {
	private Image image = new Image(-150, 100);
    private Stroke stroke = new Stroke(Color.RED, 10);
    private boolean killed = false;
    private boolean newUnicorn = true;
    private int score = 0;
    private int yChange = 0;
    public long startTime;
    public long endTime;

    public GameView(Context context) {
	    super(context);
	    setBackgroundResource(R.drawable.space);
	    image.setAndScaleImage(getResources(), R.drawable.unicorn, 150, 150, false);
    }
    
    public GameView(Context context, AttributeSet attributeSet) {
    	super(context, attributeSet);
	    setBackgroundResource(R.drawable.space);
	    image.setAndScaleImage(getResources(), R.drawable.unicorn, 150, 150, false);
    }
    
    /*
     * This method is automatically invoked when the View is displayed.
     * It is also called after you call "invalidate" on this object.
     */
    protected void onDraw(Canvas canvas) {    	

    	// resets the position of the unicorn if one is killed or reaches the right edge
    	if (newUnicorn || image.getImagePoint().x >= this.getWidth()) {
    		image.setImagePoint(-150, (int)(Math.random() * 200 + 200));
    		yChange = (int)(10 - Math.random() * 20);
    		newUnicorn = false;
    		killed = false;
    	}

		// show the exploding image when the unicorn is killed
    	if (killed) {
    	    image.setAndScaleImage(getResources(), R.drawable.explosion, 150, 150, false);
    		canvas.drawBitmap(image.getImage(), image.getImagePoint().x, image.getImagePoint().y, null);
    		newUnicorn = true;
    	    image.setAndScaleImage(getResources(), R.drawable.unicorn, 150, 150, false);
    		try { Thread.sleep(10); } catch (Exception e) { }
    		invalidate();
    		return;
    	}

    	// draws the unicorn at the specified point
		canvas.drawBitmap(image.getImage(), image.getImagePoint().x, image.getImagePoint().y, null);
    	
		// draws the stroke
    	if (stroke.getPoints().size() > 1) {
    		for (int i = 0; i < stroke.getPoints().size()-1; i++) {
    			int startX = stroke.getPoints().get(i).x;
    			int stopX = stroke.getPoints().get(i+1).x;
    			int startY = stroke.getPoints().get(i).y;
    			int stopY = stroke.getPoints().get(i+1).y;
    			Paint paint = new Paint();
    			paint.setColor(stroke.getColor());
    			paint.setStrokeWidth(stroke.getWidth());
    			canvas.drawLine(startX, startY, stopX, stopY, paint);
    		}
    	}
    	
    }

    /* 
     * This method is automatically called when the user touches the screen.
     */
    public boolean onTouchEvent(MotionEvent event) {
    	
    	if (event.getAction() == MotionEvent.ACTION_DOWN) {
    		stroke.addPoint((int)event.getX(), (int)event.getY());
    	}
    	else if (event.getAction() == MotionEvent.ACTION_MOVE) {
    		stroke.addPoint((int)event.getX(), (int)event.getY());
    	}
    	else if (event.getAction() == MotionEvent.ACTION_UP) {
    		stroke.clearPoints();
    	}
    	else {
    		return false;
    	}
    	
    	// see if the point is within the boundary of the image
    	// the !killed thing here is to prevent a "double-kill" that could occur
    	// while the "explosion" image is being shown
        if (!killed && image.isWithinBound(event.getX(), event.getY())) {
    		killed = true;
    		score++;
    		((TextView)(GameActivity.instance.getScoreboard())).setText(""+score);
    	}
    	
    	// forces a redraw of the View
    	invalidate();
    	
    	return true;
    }
    
    protected void offSetImagePoint(int dx, int dy) {
    	image.offsetImagePoint(dx, dy);
    }
    
    protected int getYChange() {
    	return yChange;
    }
    
    protected int getScore() {
    	return score;
    }
    
    
}

