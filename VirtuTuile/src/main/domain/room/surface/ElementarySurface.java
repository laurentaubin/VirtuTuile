package domain.room.surface;

import domain.room.Cover;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public abstract class ElementarySurface extends Polygon {
    private boolean isHole = false;

    public ElementarySurface(int[] xPoints, int[] yPoints, int numberOfEdges) {
        super(xPoints, yPoints, numberOfEdges);
    }

    public ElementarySurface(ElementarySurface elementarySurfaceToCopy) {
        this.isHole = new Boolean(elementarySurfaceToCopy.isHole);
    }

    public int[] getxCoord(){
        return this.xpoints;
    }

    public int[] getyCoord(){
        return this.ypoints;
    }

    public void setxCoord(int x, int index){
        this.xpoints[index] = x;
    }

    public void setyCoord(int y, int index){
        this.ypoints[index] = y;

    }

    public void updateSurface(){
        this.reset();
        for(int i = 0; i < this.getxCoord().length; i++){
            this.addPoint(this.xpoints[i], this.ypoints[i]);
        }
    }

    public boolean isHole() {
        return this.isHole;
    }

    public void setHole(boolean hole) {
        this.isHole = hole;
    }

    public void translate(double deltaX, double deltaY) {
        this.translate((int)deltaX,(int)deltaY);
    }

    public double getArea() {
        double area = 0d;
        int firstX = this.xpoints[0];
        int firstY = this.ypoints[0];

        for (int i = 1; i < xpoints.length; i++) {
            area += (xpoints[i] * firstY - ypoints[i] * firstX);
        }

        return area / 2;
    }

    public void updatePoints(double deltaX, double deltaY) {
        for (int i = 0; i < this.npoints; i++) {
            this.xpoints[i] += deltaX;
            this.ypoints[i] += deltaY;
        }
    }

    public void merge(Surface surface){

    }
}

