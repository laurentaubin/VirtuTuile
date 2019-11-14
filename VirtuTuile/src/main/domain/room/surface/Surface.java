package domain.room.surface;

import domain.room.Cover;
import gui.MainWindow;
import util.UnitConverter;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Surface {
    private Point position;
    private Color color;
    private boolean selectionStatus = false;
    private Cover cover;
    private boolean mergedStatus = false;
    private boolean haveHole = false;
    private Polygon polygon;
    private MainWindow.MeasurementUnitMode currentMode = MainWindow.MeasurementUnitMode.METRIC;
    private ArrayList<ElementarySurface> wholeSurfaces;
    private ArrayList<ElementarySurface> holes;

    public ArrayList<ElementarySurface> getWholeSurfaces() {
        return wholeSurfaces;
    }

    public ArrayList<ElementarySurface> getHoles() {
        return holes;
    }

    public Surface () {
        wholeSurfaces = new ArrayList<ElementarySurface>();
        holes = new ArrayList<ElementarySurface>();
    }

    public void updatePolygon(Polygon polygon){
        if (!mergedStatus) {
            this.polygon = polygon;
        }

        else {
            this.polygon = mergePolygon(polygon);
        }
    }

    private Polygon mergePolygon(Polygon polygon) {
        //TODO algo pour créer un polygon résultant à partir d'une liste de polygon
        return new Polygon();
    }

    public void addElementaryWholeSurface(ElementarySurface elementarySurface) {
        wholeSurfaces.add(elementarySurface);
    }

    public void addHole(ElementarySurface elementarySurface) {
        if (!haveHole) {
            haveHole = true;
        }
        holes.add(elementarySurface);
    }

    public void setColor (Color color) {
        this.color = color;
    }

    public void switchSelectionStatus() {
        this.selectionStatus = !this.selectionStatus;
    }

    public boolean isSelected() {
        return this.selectionStatus;
    }

    public void unselect() {
        this.selectionStatus = false;
    }

    public void setSelectionStatus(boolean selectionStatus) {
        this.selectionStatus = selectionStatus;
    }

    public float getWidth() {
        return 0f;
    }

    public float getHeight() {
        return 0f;
    }

     public Cover getCover() {
        return this.cover;
    }

    public void setCover(Cover cover){
        this.cover = cover;
    }

    public void updateSurface() {
        this.polygon.reset();
        for(int i = 0; i < polygon.xpoints.length; i++){
            polygon.addPoint(polygon.xpoints[i], polygon.ypoints[i]);
        }
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public double getArea() {
        double area = 0d;
        for (ElementarySurface wholeSurface : this.wholeSurfaces) {
            area += wholeSurface.getArea();
        }
        for (ElementarySurface hole : this.holes) {
            area -= hole.getArea();
        }

        return area;
    }

    public void setMeasurementMode(MainWindow.MeasurementUnitMode mode) {
        if (this.currentMode == mode) { return; }

        switch (mode) {
            case METRIC:
                this.polygon = UnitConverter.convertPolygonFromInchToMeter(this.polygon);
                this.currentMode = MainWindow.MeasurementUnitMode.METRIC;
                break;
            case IMPERIAL:
                this.polygon = UnitConverter.convertPolygonFromMeterToInch(this.polygon);
                this.currentMode = MainWindow.MeasurementUnitMode.IMPERIAL;
                break;
        }
    }
}

