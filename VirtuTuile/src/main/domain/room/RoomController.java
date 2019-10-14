package domain.room;

import java.awt.Point;
import java.util.List;

public class RoomController {
    private final Room room;

    public RoomController(Room room) {
        this.room = room;
    }

    public RoomController(){
        room = new Room();
    }

    public void addRectangularSurface() {

    }

    public void addIrregularSurface(){

    }

    public List<Surface> getSurfaceList() {
        return room.getSurfaceList();
    }

    public int getNumberOfSurfaces() {
        return room.getNumberOfSurfaces();
    }

    public void switchSelectionStatus(double x, double y) {
        room.switchSelectionStatus(x, y);
    }

}
