package domain.room.surface;

import domain.room.Cover;
import domain.room.Tile;
import domain.room.TileType;
import domain.room.pattern.DefaultPattern;
import domain.room.pattern.Pattern;
import gui.MainWindow;
import util.UnitConverter;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Surface {
    private Point position;
    private Color color;
    private boolean selectionStatus = false;
    private TileType tileType;
    private Cover cover;
    private Pattern pattern;
    private boolean mergedStatus = false;
    private boolean haveHole = false;
    private Polygon polygon;
    private MainWindow.MeasurementUnitMode currentMode = MainWindow.MeasurementUnitMode.METRIC;
    private double width;
    private double height;
    private ArrayList<ElementarySurface> wholeSurfaces;
    private ArrayList<ElementarySurface> holes;

    public ArrayList<ElementarySurface> getWholeSurfaces() {
        return wholeSurfaces;
    }

    public ArrayList<ElementarySurface> getHoles() {
        return holes;
    }

    public Surface(Point point) {
        this.position = point;
        wholeSurfaces = new ArrayList<ElementarySurface>();
        holes = new ArrayList<ElementarySurface>();
        this.tileType = TileType.createTileWithDefaultParameters();
        this.pattern = new DefaultPattern();
    }

    public void updatePolygon(Polygon polygon) {
        if (!mergedStatus) {
            if (wholeSurfaces.isEmpty()) {
                // C'est un trou
                this.polygon = new Polygon(
                        this.getHoles().get(0).xpoints,
                        this.getHoles().get(0).ypoints,
                        this.getHoles().get(0).npoints
                );
            } else {
                // C'est une surface pleine
                this.polygon = new Polygon(
                        this.getWholeSurfaces().get(0).xpoints,
                        this.getWholeSurfaces().get(0).ypoints,
                        this.getWholeSurfaces().get(0).npoints
                );
            }
            this.polygon = polygon;
        } else {
            this.polygon = polygon;
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

    public Color getColor() {
        return this.color;
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

    public double getWidth() {
        return this.getBoundingRectangle().getWidth();
    }

    public double getHeight() {
        return this.getBoundingRectangle().getHeight();
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

    public Rectangle2D getBoundingRectangle() {
        return this.polygon.getBounds2D();
    }

    public Point getPosition() {
        return this.position;
    }

    public void setPosition() {

    }

    public boolean isHole() {
        return this.isHole();
    }

    public TileType getTileType(){
        return this.tileType;
    }

    public void setTileType(TileType tileType) {
        this.tileType = tileType;
    }

    public Pattern getPattern(){
        return this.pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }
}

