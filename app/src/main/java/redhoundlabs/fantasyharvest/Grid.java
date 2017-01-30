package redhoundlabs.fantasyharvest;

import android.content.Context;
import android.graphics.Point;

import java.util.List;

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
        float x = 41;
        float y = 21;

        //convert to ints (may delete this)
        int intX = (int) x;
        int intY = (int) y;

        //initialize 2D grid
        grid  = new Pixel[intX][intY];


        //Get center of grid (starting point)
        int centerX = intX / 2;
        int centerY = intY / 2;

        // Calculate how big a single pixel should be
        int helperWidth = viewPortX * 2 + 1;
        int helperHeight = viewPortY * 2;
        pixelWidth = screenX / helperWidth;
        pixelHeight = screenY / helperHeight;

        // Used to space out pixels evenly across screen
        int counterx = -1;
        int countery = -1;

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

    /**
     * Find pixel closest to given coordinates - used for converting touches to a pixel
     * @param x x world coordinate
     * @param y y world coordinate
     * @return the Pixel closest to the given coordinates 
     */
    public Pixel worldToGrid(float x, float y)
    {   
        // store given world coordinates as ints
        int xPosition = (int) x;
        int yPosition = (int) y;
        
        // will be used to store the index of the pixel returned (this is an Integer because null needed to be stored
        Integer indexI = null;
        Integer indexJ = null;

        //Store the last pixel's position that we found
        int lastPixelPositionX = 0;
        int lastPixelPositionY = 0;

        // Iterate through grid
        for (int i = GetIndexOfPixel(topLeft).x; i <= GetIndexOfPixel(topRight).x; i++)
        {
            for (int j = GetIndexOfPixel(bottomRight).y; j >= GetIndexOfPixel(topRight).y; j--)
            {
                //If the passed in coordinates match this pixel's position return the pixel
                if ((int)grid[i][j].positionX == xPosition && (int)grid[i][j].positionY == yPosition)
                {
                    return grid[i][j];
                }
                else
                {
                    // Make a range based on a pixel's width and height and search for the nearest pixel
                    for (int k = xPosition - pixelWidth; k <= pixelWidth + xPosition; k++)
                    {

                        if (k == (int)grid[i][j].getPositionX() && Math.abs(xPosition - k) < Math.abs(xPosition - lastPixelPositionX))
                        {
                            lastPixelPositionX = (int)grid[i][j].getPositionX();
                            indexI =  new Integer(i);
                            break;
                        }
                    }

                    for (int m = yPosition - pixelHeight; m <= yPosition + pixelHeight; m++)
                    {
                        if (m == (int)grid[i][j].getPositionY() && Math.abs(yPosition - m) < Math.abs(yPosition - lastPixelPositionY))
                        {
                            lastPixelPositionY = (int)grid[i][j].getPositionY();
                            indexJ = new Integer(j);
                            break;
                        }
                    }

                    }
                }
            }

        //If at the end the indices for a pixel we found aren't null, then return a pixel else return null
        if (indexI != null && indexJ != null)
        {
            return grid[indexI][indexJ];
        }
        else
        {
            return null;
        }
    }

    public List<Pixel> searchPathPlayer(Pixel startPosition, Pixel endposition)
    {
        // TODO Actually create search algorithm
        return null;
    }

}
