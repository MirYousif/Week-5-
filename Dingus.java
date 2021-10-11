// TO BE COMPLETED 
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * Dingus --  part of HA Random Artist
 * abstract class representing an arbitrary shape
 * @author huub
 */
abstract class Dingus {
    /** random generator to be used by all subclasses of Dingus 
     *  do not change
     */
    Random random = Painting.random;

    /** postion of the shape (upper left corner) **/
    protected int x, y;

    /** color used for drawing this shape **/
    protected Color color;

    /** maximal coordinates; drawing area is (0,0)- (maxX,maxY) **/
    int maxX, maxY;

    /**
     * initializes color and position to random values
     *
     *  @param maxX, maxY - give maximum values for the position
     */    

     public Dingus(int maxX, int maxY) {
        this.maxX = maxX; this.maxY = maxY;
        // initialize to a random position
        x = random.nextInt(maxX);
        y = random.nextInt(maxY);
        // initialize to a random color
        // TODO

        int upperBound = 256;
        Random colorIndex = new Random(); 
        // gets a random number between 0 and 255. We use this to generate a random RGB value
        int red = colorIndex.nextInt(upperBound);
        int green = colorIndex.nextInt(upperBound);
        int blue = colorIndex.nextInt(upperBound);
        // creating a random color that will be used to fill the shape.
        Color color = new Color(red,green,blue);

    }

    //abstract method draw that should draw the shape on the Graphics object passed as a parameter
    abstract void draw(Graphics g); 

}
