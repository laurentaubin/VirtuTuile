package util;

//import gui.MainWindow;


import gui.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
/*import javafx.beans.binding.DoubleExpression;
import javafx.util.Pair;*/

public class UnitConverter {

    private static final float INCH_TO_METER = (float) 0.0254;
    private static final float METER_TO_INCH = (float) 39.3701;
    /*
    private static final float PIXEL_TO_METER = (float) 0.2f/39.37f;
    private static final float PIXEL_TO_INCH = (float) (0.2);

     */
    private static final float PIXEL_TO_METER = (float) 0.005;
    private static final float PIXEL_TO_INCH = (float) (0.005 * 39.37);



    //private static final float PIXEL_TO_METER = (float) 0.0002645833;
    //private static final float PIXEL_TO_INCH = (float) 0.0104166667;

/*
    private static final float INCH_TO_METER = (float) 1;
    private static final float METER_TO_INCH = (float) 1;
    private static final float PIXEL_TO_METER = (float) 1;
    private static final float PIXEL_TO_INCH = (float) 1;

*/
    public static double pixelToInch(double pixel) {
        return pixel * PIXEL_TO_INCH;
    }

    public static double pixelToMeter(double pixel) {
        return pixel * PIXEL_TO_METER;
    }

    private static double inchToPixel(double inch) {
        return inch / PIXEL_TO_INCH;
    }

    private static double meterToPixel(double meter) {
        return meter / PIXEL_TO_METER;
    }

    public static double meterToInch(double meter) {
        return meter * METER_TO_INCH;
    }

    public static double inchToMeter(double inch) {
        return inch * INCH_TO_METER;
    }

    public static double convertPixelToSelectedUnit(double pixel, MainWindow.MeasurementUnitMode mode) {
        switch (mode) {
            case METRIC:
                return UnitConverter.pixelToMeter(pixel);
            case IMPERIAL:
                return UnitConverter.pixelToInch(pixel);
        }
        return pixel;
    }

    public static double convertSelectedUnitToPixel(double unit, MainWindow.MeasurementUnitMode mode) {
        switch (mode) {
            case METRIC:
                return UnitConverter.meterToPixel(unit);
            case IMPERIAL:
                return UnitConverter.inchToPixel(unit);
        }
        return unit;
    }

    private static int[] convertMeterListToInch(int[] meterList) {
        int[] convertedList = new int[meterList.length];
        for (int i = 0; i < meterList.length; i++) {
            convertedList[i] = (int) UnitConverter.meterToInch(meterList[i]);
        }
        return convertedList;
    }

    private static int[] convertInchListToMeter(int[] inchList) {
        int[] convertedList = new int[inchList.length];
        for (int i = 0; i < inchList.length; i++) {
            convertedList[i] = (int) UnitConverter.inchToMeter(inchList[i]);
        }
        return convertedList;
    }

    public static Point convertPointToSelectedUnit(Point point, MainWindow.MeasurementUnitMode mode) {
        int xPos = (int) UnitConverter.convertPixelToSelectedUnit((int) point.getX(), mode);
        int yPos = (int) UnitConverter.convertPixelToSelectedUnit((int) point.getY(), mode);
        return new Point(xPos, yPos);

    }

    public static int[] convertPixelListToSelectedUnit(int[] pixelList, MainWindow.MeasurementUnitMode mode) {
        int[] convertedList = new int[pixelList.length];
        for (int i = 0; i < pixelList.length; i++) {
            convertedList[i] = (int) (UnitConverter.convertPixelToSelectedUnit(pixelList[i], mode));
        }
        return convertedList;
    }

    public static double[] convertSelectedUnitListToPixel(int[] unitList, MainWindow.MeasurementUnitMode mode) {
        // TODO trouver comment convertir un nombre indéterminé de points.
        // TODO checker comment ArrayLyst.toArray() marche
        double[] convertedList = new double[unitList.length];
        for (int i = 0; i < unitList.length; i++) {
            convertedList[i] = (UnitConverter.convertSelectedUnitToPixel(unitList[i], mode));
        }
        return convertedList;
    }

    public static Polygon convertPolygonToSelectedUnit(Polygon polygon, MainWindow.MeasurementUnitMode mode) {
        int[] xpoints = UnitConverter.convertPixelListToSelectedUnit(polygon.xpoints, mode);
        int[] ypoints = UnitConverter.convertPixelListToSelectedUnit(polygon.ypoints, mode);
        int npoints = polygon.npoints;

        return new Polygon(xpoints, ypoints, npoints);
    }

    /*
    public static Polygon convertPolygonToPixel(Polygon polygon, MainWindow.MeasurementUnitMode mode) {
        int[] xpoints = UnitConverter.convertSelectedUnitListToPixel(polygon.xpoints, mode);
        int[] ypoints = UnitConverter.convertSelectedUnitListToPixel(polygon.ypoints, mode);
        int npoints = polygon.npoints;

        return new Polygon(xpoints, ypoints, npoints);
    }

     */

    public static Polygon convertPolygonFromMeterToInch(Polygon polygon) {
        int[] xpoints = UnitConverter.convertMeterListToInch(polygon.xpoints);
        int[] ypoints = UnitConverter.convertMeterListToInch(polygon.ypoints);
        int npoints = polygon.npoints;

        return new Polygon(xpoints, ypoints, npoints);
    }

    public static Polygon convertPolygonFromInchToMeter(Polygon polygon) {
        int[] xpoints = UnitConverter.convertInchListToMeter(polygon.xpoints);
        int[] ypoints = UnitConverter.convertInchListToMeter(polygon.ypoints);
        int npoints = polygon.npoints;

        return new Polygon(xpoints, ypoints, npoints);
    }

    public static double stringToInch(String[] stringArray) {
        int inch = Integer.parseInt(stringArray[0]);

        String fraction = stringArray[1];

        int divisionIndex = fraction.indexOf("/");
        float numerator = Integer.parseInt(fraction.substring(0, divisionIndex));
        float denominator = Integer.parseInt(fraction.substring(divisionIndex + 1));
        float division = 0;
        if (denominator != 0) {
            division = numerator/denominator;
        }
        return inch + division;
    }
}
