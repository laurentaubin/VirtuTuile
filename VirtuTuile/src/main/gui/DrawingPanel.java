package gui;

import domain.drawing.SurfaceDrawer;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.io.Serializable;
import javax.swing.*;

//Code du zoom inspiré de https://stackoverflow.com/questions/13155382/jscrollpane-zoom-relative-to-mouse-position

public class DrawingPanel extends JPanel implements Serializable {

    public Dimension initialDimension;
    private MainWindow mainWindow;

    private float prevZoom = 1f;
    private double zoom = 1d;
    private double xOffset = 0;
    private double yOffset = 0;
    private Point zoomPoint;

    private double gridGap = 100d;
    private boolean isGridActivated = false;

    public DrawingPanel() {
    }

    public DrawingPanel(MainWindow mainWindow) {
        this.mainWindow = mainWindow;

        int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().width);
        setPreferredSize(new Dimension(width, 1));
        setVisible(true);
        int height = (int) (width * 0.5);
        initialDimension = new Dimension(800, 600);
    }



    //Le code pour l'implémentation de la grille est inspiré de "https://www.buildingjavaprograms.com/DrawingPanel.java"
    @Override
    protected void paintComponent(Graphics g) {
        if (mainWindow != null) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            mainWindow.draw(g2d, this, zoom);

            //SurfaceDrawer mainDrawer = new SurfaceDrawer(mainWindow.controller, initialDimension, mainWindow.getCurrentMeasurementMode());

            //mainDrawer.draw(g2d, zoom, prevZoom, zoomPoint, xOffset, yOffset);

            if (isGridActivated) {
                g2d.scale(zoom, zoom);

                g2d.setPaint(Color.LIGHT_GRAY);
                g2d.setStroke(new BasicStroke(0.25f));
                for (int row = 1; row <= this.getHeight() / this.gridGap; row++) {
                    g2d.drawLine(0, (int)(row * this.gridGap), this.getWidth(), (int)(row * this.gridGap));
                }
                for (int col = 1; col <= this.getWidth() / this.gridGap; col++) {
                    g2d.drawLine((int)(col * this.gridGap), 0, (int)(col * this.gridGap), this.getHeight());
                }
            }
        }
    }

    public MainWindow getMainWindow() {
        return mainWindow;
    }

    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public Dimension getInitialDimension() {
        return initialDimension;
    }

    public double getGridGap() {
        return this.gridGap;
    }

    public boolean getGridlines() {
        return this.isGridActivated;
    }

    public void setGridLines() {
        initialDimension = mainWindow.getMainScrollPaneDimension();
        this.isGridActivated = !isGridActivated;
        this.repaint();
    }

    public double getZoom() {
        return this.zoom;
    }

    public void setZoom(double zoom) {
        this.zoom = zoom;
    }

    public void zoomInActionPerformed(Point point) {
        this.setZoom(getZoom() * 1.1d);
        //this.setZoom(getZoom() * 1.1d);
        Point pos = mainWindow.getMainScrollPane().getViewport().getViewPosition();

        int newX = (int)(point.x*(1.1f - 1f) + 1.1f*pos.x);
        int newY = (int)(point.y*(1.1f - 1f) + 1.1f*pos.y);
        Point newPoint = new Point(newX, newY);
        mainWindow.setMainScrollPanePosition(newPoint);

        setDrawingPanelDimensions();

        validate();
        revalidate();
        repaint();

    }

    public void zoomOutActionPerformed(Point point) {
        this.setZoom(getZoom() * 0.9d);
        //this.setZoom(getZoom() * 0.9d);
        Point pos = mainWindow.getMainScrollPane().getViewport().getViewPosition();

        int newX = (int)(point.x*(0.9f - 1f) + 0.9f*pos.x);
        int newY = (int)(point.y*(0.9f - 1f) + 0.9f*pos.y);
        Point newPoint = new Point(newX, newY);
        mainWindow.setMainScrollPanePosition(newPoint);

        setDrawingPanelDimensions();

        validate();
        revalidate();
        repaint();
    }

    public void setDrawingPanelDimensions() {
        Dimension dimension = new Dimension((int)initialDimension.getWidth(), (int)initialDimension.getHeight());
        this.setPreferredSize(new Dimension((int)(dimension.getWidth() * zoom), (int)(dimension.getHeight() * zoom)));
        validate();
        revalidate();
    }

    public void setDrawingPanelInitialDimension() {
        this.setPreferredSize(mainWindow.getMainScrollPaneDimension());
        validate();
        revalidate();
    }

    public void setGridGap(double newGridGap) {
        this.gridGap = newGridGap;
    }
}

