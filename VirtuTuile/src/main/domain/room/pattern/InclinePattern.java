package domain.room.pattern;

import domain.room.Tile;
import domain.room.TileType;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.util.ArrayList;



public class InclinePattern extends Pattern {

    public InclinePattern() {
        super();
    }

    public ArrayList<Tile> generateTiles(Rectangle boundingRectangle, TileType tileType, Area area, double groutWidth, boolean center) {
        double xOffset = tileType.getxOffset();
        double yOffset = tileType.getyOffset();
        double tileWidth = tileType.getWidth();
        double tileHeight = tileType.getHeight();
        //TODO mettre une case decalage comme pour brick et si pas égale a zéro ça applique le décalage
        double decalage = 0;

        double angle = 30;
        angle = Math.toRadians(90 - angle);

        Point2D.Double boundingRectanglePosition = new Point2D.Double(boundingRectangle.getX(), boundingRectangle.getY());
        Point2D.Double position = new Point2D.Double(boundingRectanglePosition.getX() - tileHeight * Math.cos(angle), boundingRectangle.getY() - tileHeight * Math.sin(angle));

        Point2D.Double initPoint = new Point2D.Double(boundingRectangle.getX() + groutWidth, boundingRectangle.getY() + groutWidth);

        double boundingRectangleWidth = (int) boundingRectangle.getWidth();
        double boundingRectangleHeight = (int) boundingRectangle.getHeight();

        double numberColumn = boundingRectangleWidth / (tileType.getWidth() + groutWidth);
        if (numberColumn / (int) numberColumn != 0) {
            numberColumn = (int) (numberColumn + 1);
        }

        double numberRow = boundingRectangleHeight / (tileType.getHeight() + groutWidth);
        if (numberRow / (int) numberRow != 0) {
            numberRow = (int) (numberRow + 1);
        }

        int count = 0;
        boolean isInside;


        for (int i = 1; i <= numberColumn*numberRow; i++) {
            int[] xPoints = new int[4];
            int[] yPoints = new int[4];


            if ((i % 2 == 1)) {
                count = 0;

                if(decalage != 0 && i > 1){
                    System.out.println(decalage/100);
                    position.setLocation(position.getX() + (decalage/100 * tileType.getWidth()) * Math.sin(angle),
                            position.getY() - (decalage/100 * tileType.getWidth()) * Math.cos(angle));
                }

                do {
                    count++;
                    xPoints[0] = (int) Math.ceil(position.getX());
                    yPoints[0] = (int) Math.ceil(position.getY());

                    xPoints[1] = (int) Math.ceil(position.getX() + tileType.getWidth() * Math.sin(angle));
                    yPoints[1] = (int) Math.ceil(position.getY() - tileType.getWidth() * Math.cos(angle));

                    xPoints[2] = (int) Math.ceil(xPoints[1] + tileType.getHeight() * Math.cos(angle));
                    yPoints[2] = (int) Math.ceil(yPoints[1] + tileType.getHeight() * Math.sin(angle));

                    xPoints[3] = (int) Math.ceil(position.getX() + tileType.getHeight() * Math.cos(angle));
                    yPoints[3] = (int) Math.ceil(position.getY() + tileType.getHeight() * Math.sin(angle));

                    isInside = (boundingRectangle.contains(xPoints[0], yPoints[0]) ||
                            boundingRectangle.contains(xPoints[1], yPoints[1]) ||
                            boundingRectangle.contains(xPoints[2], yPoints[2]) ||
                            boundingRectangle.contains(xPoints[3], yPoints[3])) || count < 3;


                    virtualTileList.add(new Tile(position, xPoints, yPoints, 4));
                    if (isInside) {
                        position.setLocation(position.getX() + (tileType.getWidth() + groutWidth) * Math.sin(angle), position.getY() - (tileType.getWidth() + groutWidth) * Math.cos(angle));
                    }

                }
                while (isInside);

                position.setLocation(position.getX() + (tileType.getHeight() + groutWidth) * Math.cos(angle), position.getY() + (tileType.getHeight() + groutWidth) * Math.sin(angle));

            } else {
                count = 0;

                if(decalage != 0){
                    System.out.println(decalage/100);
                    position.setLocation(position.getX() - (decalage/100 * tileType.getWidth()) * Math.sin(angle),
                            position.getY() + (decalage/100 * tileType.getWidth()) * Math.cos(angle));
                }

                do {
                    count++;
                    xPoints[0] = (int) Math.ceil(position.getX());
                    yPoints[0] = (int) Math.ceil(position.getY());

                    xPoints[1] = (int) Math.ceil(position.getX() + tileType.getWidth() * Math.sin(angle));
                    yPoints[1] = (int) Math.ceil(position.getY() - tileType.getWidth() * Math.cos(angle));

                    xPoints[2] = (int) Math.ceil(xPoints[1] + tileType.getHeight() * Math.cos(angle));
                    yPoints[2] = (int) Math.ceil(yPoints[1] + tileType.getHeight() * Math.sin(angle));

                    xPoints[3] = (int) Math.ceil(position.getX() + tileType.getHeight() * Math.cos(angle));
                    yPoints[3] = (int) Math.ceil(position.getY() + tileType.getHeight() * Math.sin(angle));

                    isInside = (boundingRectangle.contains(xPoints[0], yPoints[0]) ||
                            boundingRectangle.contains(xPoints[1], yPoints[1]) ||
                            boundingRectangle.contains(xPoints[2], yPoints[2]) ||
                            boundingRectangle.contains(xPoints[3], yPoints[3])) || count < 3;


                    virtualTileList.add(new Tile(position, xPoints, yPoints, 4));
                    if (isInside) {
                        position.setLocation(position.getX() - (tileType.getWidth() + groutWidth) * Math.sin(angle), position.getY() + (tileType.getWidth() + groutWidth) * Math.cos(angle));
                    }

                }
                while (isInside);

                position.setLocation(position.getX() + (tileType.getHeight() + groutWidth) * Math.cos(angle), position.getY() + (tileType.getHeight() + groutWidth) * Math.sin(angle));

            }
        }
        deleteOutsideTile(area, tileWidth, tileHeight);
        return virtualTileList;
    }

                /*
        for (int row = 1; row <= numberRow*numberColumn ; row++) {

            isInside = true;




            while(isInside) {
                count++;
                int[] xPoints = new int[4];
                int[] yPoints = new int[4];

                xPoints[0] = (int) (position.getX() + xOffset);
                xPoints[1] = (int) (position.getX() + xOffset + tileType.getWidth());
                xPoints[2] = (int) (position.getX() + xOffset + tileType.getWidth());
                xPoints[3] = (int) (position.getX() + xOffset);

                yPoints[0] = (int) (position.getY() + yOffset);
                yPoints[1] = (int) (position.getY() + yOffset);
                yPoints[2] = (int) (position.getY() + yOffset + tileType.getHeight());
                yPoints[3] = (int) (position.getY() + yOffset + tileType.getHeight());


                virtualTileList.add(new Tile(position, xPoints, yPoints, 4));

//                Tile tile = new Tile(position, xPoints, yPoints, 4);
//                AffineTransform at = new AffineTransform(1, 0, 0, 1, 0, 0);
//                at.rotate(angle, xPoints[0], yPoints[0]);
//                tile.transform(at);
//                virtualTileList.add(tile);


                position.setLocation(position.getX() + ((tileType.getWidth() + groutWidth) * Math.cos(Math.toRadians(90) - angle)),
                        position.getY() +((tileType.getWidth() + groutWidth) * Math.sin(Math.toRadians(90) - angle)));

                isInside = (boundingRectangle.contains(xPoints[0],  yPoints[0]) ||
                        boundingRectangle.contains(xPoints[1],  yPoints[1]) ||
                        boundingRectangle.contains(xPoints[2],  yPoints[2]) ||
                        boundingRectangle.contains(xPoints[3],  yPoints[3])) || count < 2;


            }
            position.setLocation(initPoint.getX() + tileHeight*Math.sin((Math.PI/2) - angle), position.getY() - tileHeight*Math.cos((Math.PI/2) - angle));
            initPoint = new Point2D.Double(initPoint.getX() + tileHeight/Math.sqrt(2),position.getY() + (tileType.getHeight()/Math.sqrt(2)) );
        }
        //deleteOutsideTile(area, tileWidth, tileHeight);
        return virtualTileList;
    }


*/
    public void deleteOutsideTile(Area surface, double baseTileWidth, double baseTileHeight) {
        for (Tile tile : virtualTileList) {
            // TODO trouver meilleur facon que bounding pour l'inspecteur car les tuiles sont inclinées
            tile.intersect(surface);
            if (!tile.isEmpty()) {
                tile.setWidth(tile.getBounds2D().getWidth());
                tile.setHeight(tile.getBounds2D().getHeight());
                tile.inspect();
            }
        }
    }
}

