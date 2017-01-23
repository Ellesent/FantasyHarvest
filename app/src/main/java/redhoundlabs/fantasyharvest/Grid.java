package redhoundlabs.fantasyharvest;

import android.content.Context;
import android.graphics.Point;

/**
 * Created by cool- on 1/16/2017.
 */

public class Grid {


    Pixel[][]grid;
    public Pixel[][] getGrid()
    {
        return grid;
    }

    int pixelWidth;
    int pixelHeight;
    Pixel topRight;
    Pixel topLeft;
    Pixel bottomRight;
    Pixel bottomLeft;
    Pixel center;

    public Pixel getCenter() {return center;}
    public Pixel getTopRight(){return topRight;}
    public Pixel getTopLeft(){return topLeft;}
    public Pixel getbottomRight(){return bottomRight;}
    public Pixel getBottomLeft(){return bottomLeft;}



    public int getPixelWidth(){return pixelWidth;}
    public int getPixelHeight(){return pixelHeight;}

    public Grid(Context context, int screenX, int screenY, int viewPortX, int viewPortY)
    {
        float x = 40;
        float y = 20;

        int intX = (int) x;
        int intY = (int) y;

        grid  = new Pixel[intX][intY];

        int centerX = intX / 2 - 1;
        int centerY = intY / 2 - 1;

        int helperWidth = viewPortX * 2;
        int helperHeight = viewPortY * 2;
        pixelWidth = screenX / helperWidth;
        pixelHeight = screenY / helperHeight;

        int counterx = -1;
        int countery = -1;

        for (int i = centerX - viewPortX; i <= centerX + viewPortX; i++)
        {
            countery = -1;
            counterx++;
            for (int j = centerY + viewPortY; j >= centerY - viewPortY; j--)
            {
                countery++;
                Pixel pixel;
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

        topRight = grid[centerX + viewPortX][centerY - viewPortY];
        bottomRight = grid[centerX + viewPortX][centerY + viewPortY];

        topLeft = grid[centerX - viewPortX][centerY - viewPortY];
        bottomLeft = grid[centerX - viewPortY][centerY + viewPortY];

        center = grid[centerX][centerY];
    }

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
