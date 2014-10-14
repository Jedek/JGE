package model;
import controller.*;

/**
 * 
 */
public class Player extends Pawn {

    public Player()
    {
    }

    /*
     * Move is the main function that'll be called upon from the Controller.
     * You can do all sorts of checks here if you use more pawns.
     */
    public void move(Direction direction)
    {

        FieldArea nextArea = currentArea.getNeighbour(direction);
        if(Controller.Instance().validateArea(nextArea) == true)
        {
            moveTo(nextArea);
        }
    }

     /**
     * moveTo is the actual moving of the player pawn. The direction is already established in the Move function.
      * This private function should be called if all the checks are okay.
     * @param nextArea The next area to move too.
     */
    private void moveTo(FieldArea nextArea)
    {
        movePawnToArea(nextArea);
    }
}