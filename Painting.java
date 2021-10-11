/*
 * part of HA Random artist
 * TO BE COMPLETED
 *
 * @author huub
 * @author kees
 */

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

// import jdk.jfr.internal.OldObjectSample;

import java.io.*;

public class Painting extends JPanel implements ActionListener {

   /*---- Randomness ----*/
    /** you can change the variable SEED if you like
     *  the same program with the same SEED will generate exactly the same sequence of pictures
     */

    
    final static Random number = new Random();
    final static long num1 = number.nextInt(1000);           // ?????????????????????????????????????????????????????????????????????
    final static long SEED = num1; // seed for the random number generator; any number works

    /** do not change the following two lines **/
    final static Random random = new Random( SEED ); // random generator to be used by all classes
    int numberOfRegenerates = 0;

   /*---- Screenshots ----
    * do not change
    */
    char current = '0';
    String filename = "randomshot_"; // prefix
    
   /*---- Dinguses ----*/
    ArrayList<Dingus> shapes = new ArrayList<Dingus>();
    //...

    public Painting() {
        setPreferredSize(new Dimension(800, 450)); // make panel 800 by 450 pixels.
        //...
    }

    @Override
    protected void paintComponent(Graphics g) { // draw all your shapes
        super.paintComponent(g);     // clears the panel

        // draw all shapes
        // TODO

        for (int i = 0; i < shapes.size(); i++) {

            shapes.get(i).draw(g);

        }

            // shapes.get(0).draw(g);
            // shapes.get(1).draw(g);
            // shapes.get(2).draw(g);
            // // shapes.get(3).draw(g);


    }

    /**
     * reaction to button press
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if ( "Regenerate".equals(e.getActionCommand()) ) {
            regenerate();
            repaint();
        } else { // screenshot
            saveScreenshot( this, filename+current++ ); // ++ to show of compact code :-)
        }
    }

    void regenerate() {
        numberOfRegenerates++; // do not change
        
        // clear the shapes list
        shapes.clear();

        // create random shapes
        //Generate random number of shapes on painting between 10 and 30 inclusive
        int numShape = (int) Math.floor(Math.random()*(30-10+1)+10);

        //Array that contains a list of all the sub classes of Dingus
        // ArrayList<Dingus> shapes = new ArrayList<Dingus>();
        Dingus[] subClasses = new Dingus[4];
        CircleDingus cd = new CircleDingus(800, 450);
        TreeDingus td = new TreeDingus(800, 450);
        // IceCreamDingus id = new IceCreamDingus(800, 450);
        RectangleDingus rd = new RectangleDingus(800, 450);
        TriangleDingus ttd = new TriangleDingus(800, 450);
        // if time left over , do traffic dingus
        // ovalDingus od = new ovalDingus(800, 450);
        subClasses[0] = cd;
        subClasses[1] = td;
        // subClasses[2] = id;
        subClasses[2] = rd;
        subClasses[3] = ttd;
        // subClasses[5] = od;

        // for (int i = 0; i < 3; i++ ) {

        //     //random number used to choose one out of the 5 random dingus subclasses
        //     int numDingus = (int) Math.floor(Math.random()*(3-0+1)+0);
        //     //adds the shape to the arraylist shapes
        //     // shapes.add(subClasses[0]);
        //     // shapes.add(subClasses[1]);
        //     // shapes.add(subClasses[2]);
        //     // shapes.add(subClasses[3]);

        //     shapes.add(subClasses[i]);
   
        // }
        shapes.add(subClasses[1]);
        shapes.add(subClasses[1]);
        shapes.add(subClasses[2]);
        // shapes.add(subClasses[3]);
        shapes.add(subClasses[0]);
        shapes.add(subClasses[2]);



        

        //use random number to draw a random shape from shapes list. shapes.get(i).draw(g)
        //add shape to the panel to make a "drawing".


    }

    /** 
     * saves a screenshot of a Component on disk
     *  overides existing files
     *
     * @param component - Component to be saved
     * @param name - filename of the screenshot, followed by a sequence number
     * 
     * do not change
     */
    void saveScreenshot(Component component, String name) {
        String randomInfo = ""+SEED+"+"+ (numberOfRegenerates-1); //minus 1 because the initial picture should not count
        System.out.println( SwingUtilities.isEventDispatchThread() );
        BufferedImage image =
            new BufferedImage(
                              component.getWidth(),
                              component.getHeight(),
                              BufferedImage.TYPE_INT_RGB
                             );
        // call the Component's paint method, using
        // the Graphics object of the image.
        Graphics graphics = image.getGraphics();
        component.paint( graphics ); // alternately use .printAll(..)
        graphics.drawString(randomInfo, 0, component.getHeight());
        
        try {
            ImageIO.write(image, "PNG", new File(name+".png"));
            System.out.println(" Saved screenshot as "+ name );
        } catch( IOException e ) {
            System.out.println( "Saving screenshot failed: "+e );
        }
    }
    
}
