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

    public int getPixelWidth(){return pixelWidth;}
    public int getPixelHeight(){return pixelHeight;}

    public Grid(Context context, int screenX, int screenY)
    {
        float x = 40;
        float y = 20;

        int intX = (int) x;
        int intY = (int) y;
        grid  = new Pixel[intX][intY];

        pixelWidth = screenX / intX;
        pixelHeight = screenY / intY;

        for (int i = 0; i < intX; i++)
        {

            for (int j = 0; j < intY; j++)
            {
                Pixel pixel;
                if (i == 0 && j == 0)
                {
                    pixel = new Pixel(context, 0, 0, pixelWidth, pixelHeight);
                }
                else if (i == 0)
                {
                    pixel = new Pixel(context, 0, screenY / intY * j, pixelWidth, pixelHeight);
                }
                else if (j == 0)
                {
                    pixel = new Pixel(context, screenX /intX * i, 0, pixelWidth, pixelHeight);
                }
                else
                {
                    pixel = new Pixel(context, screenX /intX * i, screenY / intY * j, pixelWidth, pixelHeight);
                }
                grid[i][j] = pixel;
            }
        }
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
