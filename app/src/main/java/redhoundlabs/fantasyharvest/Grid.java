package redhoundlabs.fantasyharvest;

import android.content.Context;

/**
 * Created by cool- on 1/16/2017.
 */

public class Grid {


    Pixel[][]grid;
    public Pixel[][] getGrid()
    {
        return grid;
    }

    public Grid(Context context, int screenX, int screenY)
    {
        float x = 10;
        float y = 10;

        int intX = (int) x;
        int intY = (int) y;
        grid  = new Pixel[intX][intY];





        for (int i = 0; i < intX; i++)
        {

            for (int j = 0; j < intY; j++)
            {
                Pixel pixel;
                if (i == 0 && j == 0)
                {
                    pixel = new Pixel(context, 0, 0, screenX / intX, screenY / intY);
                }
                else if (i == 0)
                {
                    pixel = new Pixel(context, 0, screenY / intY * j, screenX / intX, screenY / intY);
                }
                else if (j == 0)
                {
                    pixel = new Pixel(context, screenX /intX * i, 0, screenX / intX, screenY / intY);
                }
                else
                {
                    pixel = new Pixel(context, screenX /intX * i, screenY / intY * j, screenX / intX, screenY / intY);
                }
                grid[i][j] = pixel;
            }
        }
    }

}
