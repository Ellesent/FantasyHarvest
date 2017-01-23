package redhoundlabs.fantasyharvest;

import android.graphics.Point;

/**
 * Created by cool- on 1/22/2017.
 */

public class Camera {

    Point centerPixelIndex;
    Pixel topRight;
    Pixel topLeft;
    Pixel bottomRight;
    Pixel bottomLeft;

    public Camera(Grid grid, Pixel centerPixel, int pixelsAwayX, int pixelsAwayY)
    {
        centerPixelIndex = grid.GetIndexOfPixel(centerPixel);

    }
}
