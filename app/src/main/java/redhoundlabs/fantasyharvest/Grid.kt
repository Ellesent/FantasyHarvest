package redhoundlabs.fantasyharvest

import android.content.Context
import android.graphics.Point

/**
 * Class for Grid
 */

class Grid
/**
 * Constructor class for the grid
 * @param context context
 * *
 * @param screenX Screen's width
 * *
 * @param screenY Screen's height
 * *
 * @param viewPortX Camera's width
 * *
 * @param viewPortY Camera's height
 */
(context: Context, screenX: Int, screenY: Int, viewPortX: Int, viewPortY: Int) {


     var  grid: Array<Array<Pixel?>>
        internal set      // 2D array for grid

    var pixelWidth: Int = 0
        internal set     // How wide an individual pixel should be
    var pixelHeight: Int = 0
        internal set    // How long an individual pixel should be

    var topRight: Pixel
        internal set     // Store the pixels in the corners of the screen
    var topLeft: Pixel
        internal set
    internal var bottomRight: Pixel
    var bottomLeft: Pixel
        internal set

    // Properties
    var center: Pixel
        internal set       // Store the center of the grid

    fun getbottomRight(): Pixel {
        return bottomRight
    }

    init {
        //How big the 2D grid should be
        val x = 41f
        val y = 21f

        //convert to ints (may delete this)
        val intX = x.toInt()
        val intY = y.toInt()

        //initialize 2D grid
        grid =  Array(intX) { arrayOfNulls<Pixel>(intY) }


        //Get center of grid (starting point)
        val centerX = intX / 2
        val centerY = intY / 2

        // Calculate how big a single pixel should be
        val helperWidth = viewPortX * 2 + 1
        val helperHeight = viewPortY * 2
        pixelWidth = screenX / helperWidth
        pixelHeight = screenY / helperHeight

        // Used to space out pixels evenly across screen
        var counterx = -1
        var countery = -1

        // Grid construction - generate columns and rows of pixels
        for (i in centerX - viewPortX..centerX + viewPortX) {
            countery = -1
            counterx++
            for (j in centerY + viewPortY downTo centerY - viewPortY) {
                countery++
                val pixel: Pixel

                // Place pixels based on position in graph
                if (i == 0 && j == 0) {
                    pixel = Pixel(context, 0f, 0f, pixelWidth, pixelHeight)
                } else if (i == 0) {
                    pixel = Pixel(context, 0f, (screenY / helperHeight * countery).toFloat(), pixelWidth, pixelHeight)
                } else if (j == 0) {
                    pixel = Pixel(context, (screenX / helperWidth * counterx).toFloat(), 0f, pixelWidth, pixelHeight)
                } else {
                    pixel = Pixel(context, (screenX / helperWidth * counterx).toFloat(), (screenY / helperHeight * countery).toFloat(), pixelWidth, pixelHeight)
                }
                grid[i][j] = pixel
            }
        }

        // Store corner and center pixels
        topRight = grid[(centerX + viewPortX)][(centerY - viewPortY)]!!
        bottomRight = grid[centerX + viewPortX][centerY + viewPortY]!!

        topLeft = grid[centerX - viewPortX][centerY - viewPortY]!!
        bottomLeft = grid[centerX - viewPortY][centerY + viewPortY]!!

        center = grid[centerX][centerY]!!
    }

    /**
     * Get what index a pixel is located at in the graph since Java doesn't have this implemented already wtf
     * @param pixel what pixel to find the index of
     * *
     * @return point in graph
     */
    fun GetIndexOfPixel(pixel: Pixel): Point? {
        if (grid != null) {
            for (i in grid.indices) {
                for (j in 0..grid[0].size - 1) {

                    if (grid[i][j] != null && grid[i][j] === pixel) {
                        return Point(i, j)
                    }

                }
            }
        } else {
            throw NullPointerException()
        }
        return null
    }

    /**
     * Find pixel closest to given coordinates - used for converting touches to a pixel
     * @param x x world coordinate
     * *
     * @param y y world coordinate
     * *
     * @return the Pixel closest to the given coordinates
     */
    fun worldToGrid(x: Float, y: Float): Pixel? {
        // store given world coordinates as ints
        val xPosition = x.toInt()
        val yPosition = y.toInt()

        // will be used to store the index of the pixel returned (this is an Integer because null needed to be stored
        var indexI: Int? = null
        var indexJ: Int? = null

        //Store the last pixel's position that we found
        var lastPixelPositionX = 0
        var lastPixelPositionY = 0

        // Iterate through grid
        for (i in GetIndexOfPixel(topLeft)!!.x..GetIndexOfPixel(topRight)!!.x) {
            for (j in GetIndexOfPixel(bottomRight)!!.y downTo GetIndexOfPixel(topRight)!!.y) {
                //If the passed in coordinates match this pixel's position return the pixel
                if (grid[i][j]!!.positionX.toInt() == xPosition && grid[i][j]!!.positionY.toInt() == yPosition) {
                    return grid[i][j]
                } else {
                    // Make a range based on a pixel's width and height and search for the nearest pixel
                    for (k in xPosition - pixelWidth..pixelWidth + xPosition) {

                        if (k == grid[i][j]!!.positionX.toInt() && Math.abs(xPosition - k) < Math.abs(xPosition - lastPixelPositionX)) {
                            lastPixelPositionX = grid[i][j]!!.positionX.toInt()
                            indexI = i
                            break
                        }
                    }

                    for (m in yPosition - pixelHeight..yPosition + pixelHeight) {
                        if (m == grid[i][j]!!.positionY.toInt() && Math.abs(yPosition - m) < Math.abs(yPosition - lastPixelPositionY)) {
                            lastPixelPositionY = grid[i][j]!!.positionY.toInt()
                            indexJ = j
                            break
                        }
                    }

                }
            }
        }

        //If at the end the indices for a pixel we found aren't null, then return a pixel else return null
        if (indexI != null && indexJ != null) {
            return grid!![indexI][indexJ]
        } else {
            return null
        }
    }

    fun searchPathPlayer(startPosition: Pixel, endposition: Pixel): List<Pixel>? {
        // TODO Actually create search algorithm
        return null
    }

}
