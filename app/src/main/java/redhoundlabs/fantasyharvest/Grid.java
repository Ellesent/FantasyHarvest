package redhoundlabs.fantasyharvest;

import android.content.Context;
import android.graphics.Point;

/**
 * Class for Grid
 */

public class Grid {


    Pixel[][]grid;      // 2D array for grid


    int pixelWidth;     // How wide an individual pixel should be
    int pixelHeight;    // How long an individual pixel should be

    Pixel topRight;     // Store the pixels in the corners of the screen
    Pixel topLeft;
    Pixel bottomRight;
    Pixel bottomLeft;

    Pixel center;       // Store the center of the grid

    // Properties
    public Pixel getCenter() {return center;}
    public Pixel getTopRight(){return topRight;}
    public Pixel getTopLeft(){return topLeft;}
    public Pixel getbottomRight(){return bottomRight;}
    public Pixel getBottomLeft(){return bottomLeft;}
    public Pixel[][] getGrid()
    {
        return grid;
    }
    public int getPixelWidth(){return pixelWidth;}
    public int getPixelHeight(){return pixelHeight;}

    /**
     * Constructor class for the grid
     * @param context context
     * @param screenX Screen's width
     * @param screenY Screen's height
     * @param viewPortX Camera's width
     * @param viewPortY Camera's height
     */
    public Grid(Context context, int screenX, int screenY, int viewPortX, int viewPortY)
    {
        //How big the 2D grid should be
        float x = 40;
        float y = 20;

        //convert to ints (may delete this)
        int intX = (int) x;
        int intY = (int) y;

        //initialize 2D grid
        grid  = new Pixel[intX][intY];


        //Get center of grid (starting point)
        int centerX = intX / 2 - 1;
        int centerY = intY / 2 - 1;

        // Calculate how big a single pixel should be
        int helperWidth = viewPortX * 2;
        int helperHeight = viewPortY * 2;
        pixelWidth = screenX / helperWidth;
        pixelHeight = screenY / helperHeight;

        // Used to space out pixels evenly across screen
        int counterx = -1;
        int countery = -1;

        //TODO Make individual for loops that branch from center pixel so player is at center of screen

        // Grid construction - generate columns and rows of pixels
        for (int i = centerX - viewPortX; i <= centerX + viewPortX; i++)
        {
            countery = -1;
            counterx++;
            for (int j = centerY + viewPortY; j >= centerY - viewPortY; j--)
            {
                countery++;
                Pixel pixel;

                // Place pixels based on position in graph
                if (i == 0 && j == 0)
                {
                    pixel = new Pixel(context, 0, 0, pixelWidth, pixelHeight);
                }
                else if (i == 0)
                {
                    pixel = new Pixel(context, 0, screenY / helperHeight * countery, pixelWidth, pixelHeight);
                }
                else if (j == 0)
                {
                    pixel = new Pixel(context, screenX /helperWidth * counterx, 0, pixelWidth, pixelHeight);
                }
                else
                {
                    pixel = new Pixel(context, screenX /helperWidth * counterx, screenY / helperHeight * countery, pixelWidth, pixelHeight);
                }
                grid[i][j] = pixel;
            }
        }

        // Store corner and center pixels
        topRight = grid[centerX + viewPortX][centerY - viewPortY];
        bottomRight = grid[centerX + viewPortX][centerY + viewPortY];

        topLeft = grid[centerX - viewPortX][centerY - viewPortY];
        bottomLeft = grid[centerX - viewPortY][centerY + viewPortY];

        center = grid[centerX][centerY];
    }

    /**
     * Get what index a pixel is located at in the graph since Java doesn't have this implemented already wtf
     * @param pixel what pixel to find the index of
     * @return point in graph
     */
    public Point GetIndexOfPixel(Pixel pixel)
    {
        if (grid != null)
        {
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {

                    if (grid[i][j] != null && grid[i][j] ==  pixel)
                    {
                        return new Point(i,j);
                    }

                }
            }
        }
        else
        {
            throw new NullPointerException();
        }
        return null;
    }

}
