package model;

import controller.Direction;
import java.util.*;

/**
 *
 * @author Jedek
 */
public class FieldArea {

    /**
     * The needed attributes of the FieldArea. Each area can have 4 neighbours. Up, down, left and right.
     */
    private HashMap<String, FieldArea> neighbours;
    private int pixelY;
    private int pixelX;


    /**
     * The constructor of FieldArea. When a new fieldarea is created, the pixel coordinates are given for the location.
     * @param Location of the fieldarea.
     */
    public FieldArea(int pixelY, int pixelX)
    {
        this.pixelY = pixelY;
        this.pixelX = pixelX;

        neighbours = new HashMap<String, FieldArea>();
    }

    /**
     * setNeighbour is responsible for adding fieldarea neighbours to this fieldarea.
     * @param direction the direction of the neighbour relative to this fieldarea. Is the neighbour on the left or right? etc..
     * @param neighbour The fieldarea object that is the neighbour
     */
    public void setNeighbour(Direction direction, FieldArea neighbour)
    {
        neighbours.put(direction.toString(), neighbour);
    }

    /**
     * getNeighbour returns the neighbour fieldarea at the right direction.
     * @param direction The direction of the neighbour relative to this fieldarea. Left gets the neighbour on the left side of this area etc.
     * @return The neighbour
     */
    public FieldArea getNeighbour(Direction direction)
    {
        return neighbours.get(direction.toString());
    }

    /**
     * getNeighbours returns all the neighbours of this fieldarea.
     * @return The neighbours of this area.
     */
    public HashMap getNeighbours()
    {
        return neighbours;
    }

    /**
     * getPixelY retrieves the Y pixel of this area.
     * @return pixelY
     */
    public int getPixelY()
    {
        return pixelY;
    }

    /**
     * getPixelX retrieves the X pixel of this area.
     * @return pixelX
     */
    public int getPixelX()
    {
        return pixelX;
    }

}