package HW8;
/**
 */

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 */
public final class DisplayImage {

    private static final int BW_THRESH = 128;
    private static final int RED_THRESH = 200;
    private static final int GREEN_BLUE_THRESH = 50;
    private BufferedImage image;               // the rasterized image
    private JFrame frame;                      // on-screen view
    private String filename;                   // name of file
    private int width, height;                 // width and height

    /**
     * Creates a picture by reading in a .png, .gif, or .jpg from
     * the given filename or URL name.
     *
     * @param filename image file name
     */
    public DisplayImage (String filename) {
        this.filename = filename;
        try {
            // try to read from file in working directory
            File file = new File (filename);
            image  = ImageIO.read (file);
            width  = image.getWidth (null);
            height = image.getHeight (null);
        }
        catch (IOException e) {
            throw new RuntimeException ("Could not open file: " + filename);
        }

        // check that image was read in
        if (image == null) {
            throw new RuntimeException ("Invalid image file: " + filename);
        }
    }

    public DisplayImage (int width, int height) {
        this.width  = width;
        this.height = height;
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);

        // check that image was read in
        if (image == null) {
            throw new RuntimeException ("Invalid image file: " + filename);
        }
    }

    /**
     * Returns a JLabel containing this Picture, for embedding in a JPanel,
     * JFrame or other GUI widget.
     *
     * @return label
     */
    private JLabel getJLabel() {
        if (image == null) {         // no image available
            return null;
        }
        ImageIcon icon = new ImageIcon (image);
        return new JLabel(icon);
    }

    /**
     * Displays the picture in a window on the screen.
     */
    public void show() {

        // create the GUI for viewing the image if needed
        if (frame == null) {
            frame = new JFrame();
            frame.setContentPane (getJLabel());
            frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
            frame.setTitle (filename);
            frame.setResizable (false);
            frame.pack();
            frame.setVisible (true);
        }

        // draw
        frame.repaint();
    }

    /**
     * Returns the height of the picture in pixels.
     *
     * @return height
     */
    public int height() {
        return height;
    }

    /**
     * Returns the width of the picture in pixels.
     *
     * @return width
     */
    public int width() {
        return width;
    }

    /**
     * Returns the color of pixel (i, j).
     *
     * @param x x coord
     * @param y y coord
     * @return color
     */
    public Color get (int x, int y) {
        return new Color (image.getRGB (x, y));
    }

    /**
     * Sets the color of pixel (i, j) to c.
     *
     * @param x x coord
     * @param y y coord
     * @param c color
     */
    public void set (int x, int y, Color c) {
        if (c == null)
            throw new IllegalArgumentException ("Can't set Color to null");
        image.setRGB (x, y, c.getRGB());
    }

    /**
     * Checks whether pixel x, y is on or off.
     *
     * @param x x coord
     * @param y y coord
     * @return true if pixel x, y is on, false if it is off.
     */
    public boolean isOn (int x, int y) {

        Color c = new Color (image.getRGB (x, y));
        int intensity = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
        return intensity < BW_THRESH;
    }

    /**
     * Checks whether pixel x, y is on or off.
     *
     * @param x x coord
     * @param y y coord
     * @return true if pixel x, y is on, false if it is off.
     */
    public boolean isRed (int x, int y) {

        Color c = new Color (image.getRGB (x, y));
        return c.getRed() > RED_THRESH && c.getGreen() < GREEN_BLUE_THRESH && c.getBlue() < GREEN_BLUE_THRESH;
    }

    /**
     * Save the picture to a file in a standard image format.
     *
     * @param filename name of file to save to.
     */
    public void save (String filename) {

        this.filename = filename;
        File file = new File (filename);
        if (frame != null) { frame.setTitle(filename); }
        String suffix = filename.substring (filename.lastIndexOf('.') + 1);
        suffix = suffix.toLowerCase();
        if (suffix.equals("jpg") || suffix.equals("png")) {
            try {
                ImageIO.write(image, suffix, file);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            throw new IllegalArgumentException ("Filename must end in .jpg or .png");
        }
    }

    /**
     * Test client. Reads a picture specified by the command-line argument,
     * and shows it in a window on the screen.
     *
     * @param args command line arguments
     */
    public static void main (String[] args) {

        DisplayImage pic = new DisplayImage (args[0]);
        System.out.printf ("%d-by-%d\n", pic.width(), pic.height());
        pic.show();
    }
}
