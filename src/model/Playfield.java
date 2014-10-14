package model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;
import javax.swing.JPanel;

import controller.*;

/**
 *
 * @author Admin
 */
public class Playfield extends JPanel implements ActionListener {

    private static final int FIELDAREAY = 6; // How many areas you want on the Y-axis (Vertically)
    private static int FIELDAREAX = 8; // How many areas you want on the X-axis (Horizontally)
    private static int FIELDAREAHEIGHT = 50; // Height of the individual Areas
    private static int FIELDAREAWIDTH = 50; // Width of the individual areas

    private static int PLAYFIELDHEIGHT = 0; // Total height of the playfield calculated in the constructor
    private static int PLAYFIELDWIDTH = 0; // Total width of the playfield calculated in the constructor

    private FieldArea[][] fieldAreas; // Array of all the fieldareas

    private Timer timer; // Timer needed to call the repaint function every so many milliseconds

    private Player player; // The player pawn, made in the Controller and given during the Initialize() function

    private ArrayList<FieldArea> chosenFieldAreas = new ArrayList<FieldArea>(); // Occupied fields, can be needed when you want to randomly generate your field and prevent pawns from standing on eachother

    public Playfield()
    {
        addKeyListener(new TAdapter());


        PLAYFIELDHEIGHT = FIELDAREAY * FIELDAREAHEIGHT + 2;
        PLAYFIELDWIDTH = FIELDAREAX * FIELDAREAWIDTH + 2;
        setSize(PLAYFIELDWIDTH, PLAYFIELDHEIGHT);
        setVisible(true);
        setFocusable(true);

        timer = new Timer();

        TimerTask refresh = new TimerTask()
        {
             public void run()
             {
                repaint();
             }
        };

        timer.scheduleAtFixedRate(refresh, 100, 100);
    }

    public void generatePlayField()
    {
        fieldAreas = new FieldArea[FIELDAREAY][FIELDAREAX];

        for(int y = 0; y < FIELDAREAY; y++)
        {
            for(int x = 0; x < FIELDAREAX; x++)
            {
                int height = y * FIELDAREAHEIGHT;
                int width = x * FIELDAREAWIDTH;

                FieldArea newArea = new FieldArea(height , width);

                if(x!= 0)
                {
                    FieldArea leftNeighbour = fieldAreas[y][x - 1];
                    leftNeighbour.setNeighbour(Direction.RIGHT, newArea);

                    newArea.setNeighbour(Direction.LEFT, leftNeighbour);
                }

                if(y != 0)
                {
                    FieldArea upNeighbour = fieldAreas[y - 1][x];
                    upNeighbour.setNeighbour(Direction.DOWN, newArea);

                    newArea.setNeighbour(Direction.UP, upNeighbour);
                }

                fieldAreas[y][x] = newArea;
            }
        }

        FieldArea playerStartingLocation = fieldAreas[0][0];
        player.setCurrentArea(playerStartingLocation); // In this basic engine, player always starts top left
        chosenFieldAreas.add(playerStartingLocation); // We give the field to the arrayList so that it is known that in future generating functions, this area can't be chosen to put another pawn on

        repaint();
    }

    public void setPlayer(Player player)
    {
        this.player = player;
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;

        for(int y = 0; y < FIELDAREAY; y++)
        {
            for(int x = 0; x < FIELDAREAX; x++)
            {
                FieldArea fa = fieldAreas[y][x];

                //g2d.drawImage(getImage(v.getPlaatje()), v.getPixelX(), v.getPixelY(), this);

               g2d.drawRect(fa.getPixelX(), fa.getPixelY(), FIELDAREAHEIGHT, FIELDAREAWIDTH);
            }
        }

        if(player != null)
        {
            g2d.drawOval(player.getCurrentAreaPixelX(), player.getCurrentAreaPixelY(), FIELDAREAHEIGHT, FIELDAREAWIDTH);
        }

       g.dispose();
    }
    
      /**
     * The actionperformed function is called every time the timer "ticks".
     * Here it repaints every time an action is performed
     * @param e
     */
    public void actionPerformed(ActionEvent e)
    {
        repaint();
    }

     /**
     * The KeyAdapter listens to every key event
     */
    private class TAdapter extends KeyAdapter {


        @Override
        public void keyReleased(KeyEvent e)
        {
            //The code of the released Key
            int key = e.getKeyCode();

            //The direction
            Direction direction = null;

            //We check each keycode and relate a direction to it
            if(key == e.VK_UP)
            {
                direction = Direction.UP;
            }

            if(key == e.VK_DOWN)
            {
                direction = Direction.DOWN;
            }

            if(key == e.VK_LEFT)
            {
                direction = Direction.LEFT;
            }

            if(key == e.VK_RIGHT)
            {
                direction = Direction.RIGHT;
            }

            if(direction != null)
            {
                Controller.Instance().movePlayer(direction);
            }

            repaint();
        }
    }

}