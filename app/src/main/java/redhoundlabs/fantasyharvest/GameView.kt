package redhoundlabs.fantasyharvest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

/***
 * Class for the view of the game - takes care of updating, drawing, etc.
 */

class GameView// When the we initialize (call new()) on gameView
// This special constructor method runs
(context: Context, // The size of the screen in pixels
 screenX: Int, screenY: Int) : SurfaceView(context), Runnable {

    // This is our thread
    private var gameThread: Thread? = null

    // Our SurfaceHolder to lock the surface before we draw our graphics
    private val ourHolder: SurfaceHolder = holder

    // A boolean which we will set and unset
    // when the game is running- or not.
    @Volatile private var playing: Boolean = false

    // Game is paused at the start
    private val paused = true

    // A Canvas and a Paint object
    private var canvas: Canvas? = null
    private val paint: Paint = Paint()

    // This variable tracks the game frame rate
    private var fps: Long = 0

    // This is used to help calculate the fps
    private var timeThisFrame: Long = 0

    // width and height of grid
    internal var gridWidth: Int = 0
    internal var gridHeight: Int = 0

    // Instance of grid class
    internal var grid: Grid = Grid(context, screenX, screenY, 6, 6)

    // Instance of player class
    internal var player: Player

    init {

        // Initialize ourHolder and paint objects

        // Initialize grid class as well as store the width and height of the grid
        gridWidth = grid.grid.size
        gridHeight = grid.grid[0].size

        //Initialize the player class
        player = Player(context, grid.center, grid.pixelWidth, grid.pixelHeight * 2)
    }// The next line of code asks the
    // SurfaceView class to set up our object.
    // Make a globally available copy of the context so we can use it in another method
    // Save width and height of screen

    override fun run() {
        while (playing) {

            // Capture the current time in milliseconds in startFrameTime
            val startFrameTime = System.currentTimeMillis()

            // Update the frame
            if (!paused) {
                update()
            }

            // Draw the frame
            draw()

            // Calculate the fps this frame
            // We can then use the result to
            // time animations and more.
            timeThisFrame = System.currentTimeMillis() - startFrameTime
            if (timeThisFrame >= 1) {
                fps = 1000 / timeThisFrame
            }
        }
    }

    private fun update() {

        // TODO Make update method for player and call here
        // TODO Make update method for grid? and call here - used for camera
    }

    private fun draw() {
        // Make sure our drawing surface is valid or we crash
        if (ourHolder.surface.isValid) {

            // Lock the canvas ready to draw
            canvas = ourHolder.lockCanvas()

            // Draw the background color
            canvas!!.drawColor(Color.argb(255, 26, 128, 182))

            for (i in grid.GetIndexOfPixel(grid.topLeft)!!.x..grid.GetIndexOfPixel(grid.topRight)!!.x) {
                for (j in grid.GetIndexOfPixel(grid.bottomRight)!!.y downTo grid.GetIndexOfPixel(grid.topRight)!!.y) {
                    // TEMPORARY - Used to make sure Player position is correct pixel in grid - for now just make it transparent
                    if (grid.grid[i][j] === player.position) {
                        paint.color = Color.argb(0, 255, 0, 0)

                    } else {
                        paint.color = Color.argb(255, 255, 255, 255)
                    }
                    canvas!!.drawBitmap(grid.grid[i][j]!!.bitmap, grid.grid[i][j]!!.positionX, grid.grid[i][j]!!.positionY, paint)
                }
            }

            //TEMPORARY - Make Player semi-transparent in order to see pixels underneath player
            paint.color = Color.argb(100, 255, 255, 255)
            canvas!!.drawBitmap(player.bitmap, player.position.positionX, player.position.positionY - player.halfSprite, paint)

            // Change the brush color
            paint.color = Color.argb(255, 249, 129, 0)
            paint.textSize = 40f

            // Draw everything to the screen
            ourHolder.unlockCanvasAndPost(canvas)
        }
    }

    // If Activity is paused/stopped
    // shutdown our thread.
    fun pause() {
        playing = false
        try {
            gameThread!!.join()
        } catch (e: InterruptedException) {
            Log.e("Error:", "joining thread")
        }

    }

    // If Activity is started then
    // start our thread.
    fun resume() {
        playing = true
        gameThread = Thread(this)
        gameThread!!.start()


    }

    // The SurfaceView class implements onTouchListener
    // So we can override this method and detect screen touches.
    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {

        when (motionEvent.action and MotionEvent.ACTION_MASK) {

        // Player has touched the screen
            MotionEvent.ACTION_DOWN -> {
            }

        // Player has removed finger from screen
            MotionEvent.ACTION_UP -> {
                val test = grid.worldToGrid(motionEvent.x, motionEvent.y)
                test?.positionX
            }
        }
        return true
    }


}