package gui;

import domain.drawing.SurfaceDrawer;
import domain.room.RoomController;
import domain.room.surface.Surface;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.*;

public class DrawingPanel extends JPanel implements Serializable {

    public Dimension initialDimension;
    private MainWindow mainWindow;

    private float prevZoom = 1f;
    private double zoom = 1d;
    private double xOffset = 0;
    private double yOffset = 0;
    private Point zoomPoint;

    private double gridGap = 10d;
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
            SurfaceDrawer mainDrawer = new SurfaceDrawer(mainWindow.controller, initialDimension);

            mainDrawer.draw(g2d, zoom, prevZoom, zoomPoint, xOffset, yOffset);
            if (isGridActivated) {

                g2d.setPaint(Color.GRAY);
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

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public void zoomInActionPerformed(Point point) {
        zoom *= 1.1;
        this.zoomPoint = point;
        zoomActionPerformed();
        repaint();
    }

    public void zoomOutActionPerformed(Point point) {
        zoom *= 0.9;
        this.zoomPoint = point;
        zoomActionPerformed();
        repaint();
    }

    public void setDrawingPanelDimensions() {
        this.setPreferredSize(new Dimension((int)(getWidth() * zoom), (int)(getHeight() * zoom)));
    }


    public void zoomActionPerformed() {
        Point pos = mainWindow.getMainScrollPane().getViewport().getViewPosition();

        this.gridGap *= zoom;
        setDrawingPanelDimensions();
        this.validate();
        //mainWindow.setMainScrollPaneDimension(size);
        //this.setSize(size);

        this.revalidate();
        this.repaint();
        mainWindow.setStatusBarText(" ");

        /*

        for (Surface surface : RoomController.getSurfaceList()) {
            if (surface.getType() == "RECTANGULAR") {
                int[] x = surface.getxCoord();
                int[] y = surface.getyCoord();
                int deltaX = (int) (x[0] * zoom - x[0]);
                int deltaY = (int) (y[0] * zoom - y[0]);
                if (deltaX > 5 && deltaY > 5 && zoom > 1) {
                    for (int i = 0; i < x.length; i++) {
                        surface.setxCoord((int) ((x[i] * zoom) - deltaX), i);
                        surface.setyCoord((int) ((y[i] * zoom) - deltaY), i);
                    }
                } else if (deltaX < -5 && deltaY < -5 && zoom < 1) {
                    for (int i = 0; i < x.length; i++) {
                        surface.setxCoord((int) ((x[i] * zoom) - deltaX), i);
                        surface.setyCoord((int) ((y[i] * zoom) - deltaY), i);
                    }
                }
            } else {
                //TODO après surface irregulière
            }
            surface.updateSurface();
            this.repaint();
        }
         */
    }
}

