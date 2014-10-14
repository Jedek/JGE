package controller;

public enum Direction {

       /**
     * These are the four directions we use in this engine. By using
        * this enumeration, there cannot ever be a mistake of using a wrong direction.
        * USAGE: Import this enumeration to every class that'll use directions. (Pawn and FieldArea for the most part).
        * Once imported, use Direction.UP etc to use the enumeration.
     */
    UP("up"), DOWN("down"), LEFT("left"), RIGHT("right");

    private String command;

    Direction(String getCommand){
        this.command = getCommand;
    }

    @Override
    public String toString(){
        return command;
    }
}