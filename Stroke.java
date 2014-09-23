package edu.upenn.cis573.hwk2;

import java.util.ArrayList;
import android.graphics.Color;
import android.graphics.Point;

public class Stroke {
	
    private ArrayList<Point> points;
    private int lineColor;
    private int lineWidth;
    
    public Stroke(int color, int width) {
		points = new ArrayList<Point>();
		lineColor = color;
		lineWidth = width;
	}
    
    protected ArrayList<Point> getPoints() {
    	return points;
    }
    
    protected int getColor() {
    	return lineColor;
    }
    
    protected int getWidth() {
    	return lineWidth;
    }
    
    protected void clearPoints() {
    	points.clear();
    }
    
    protected void addPoint(int x, int y) {
    	points.add(new Point(x, y));
    }

}
