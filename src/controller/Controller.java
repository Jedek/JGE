package controller;

import model.*;
import java.util.*;

public class Controller extends Observable {

    private static Controller controller = new Controller();
    private Player player;
    private Playfield playField;


    public void initialize()
    {
        playField = new Playfield();
        player = new Player();
        playField.setPlayer(player);
        playField.generatePlayField();
    }

    public static Controller Instance() {
        return controller;
    }

    public Player getPlayer()
    {
        return player;
    }

    public Playfield getPlayField()
    {
        return playField;
    }

    public void movePlayer(Direction direction)
    {
        player.move(direction);
    }

    public Boolean validateArea(FieldArea nextArea)
    {
        // Here you validate the next area for whatever pawns it might have.
        // Right now we only check if there is an area to move too, or not.

        if(nextArea != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}