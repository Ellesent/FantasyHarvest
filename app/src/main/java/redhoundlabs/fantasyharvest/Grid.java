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
        float x = screenX / 32;
        float y = screenY / 32;

        int intX = (int) x;
        int intY = (int) y;
        grid  = new Pixel[intX][intY];





        for (int i = 0; i < intX; i++)
        {
            for (int j = 0; j < intY; j++)
            {
                Pixel pixel = new Pixel(context, i * 32,j * 32);
                grid[i][j] = pixel;
            }
        }
    }

}
