package redhoundlabs.fantasyharvest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/***
 * Class for the view of the game - takes care of updating, drawing, etc.
 */

public class GameView extends SurfaceView implements Runnable {

    Context context;

    // This is our thread
    private Thread gameThread = null;

    // Our SurfaceHolder to lock the surface before we draw our graphics
    private SurfaceHolder ourHolder;

    // A boolean which we will set and unset
    // when the game is running- or not.
    private volatile boolean playing;

    // Game is paused at the start
    private boolean paused = true;

    // A Canvas and a Paint object
    private Canvas canvas;
    private Paint paint;

    // This variable tracks the game frame rate
    private long fps;

    // This is used to help calculate the fps
    private long timeThisFrame;

    // The size of the screen in pixels
    private int screenX;
    private int screenY;

    // width and height of grid
    int gridWidth;
    int gridHeight;

    // Instance of grid class
    Grid grid;

    // Instance of player class
    Player player;

    // When the we initialize (call new()) on gameView
    // This special constructor method runs
    public GameView(Context context, int x, int y) {

        // The next line of code asks the
        // SurfaceView class to set up our object.
        super(context);

        // Make a globally available copy of the context so we can use it in another method
        this.context = context;

        // Initialize ourHolder and paint objects
        ourHolder = getHolder();
        paint = new Paint();

        // Save width and height of screen
        screenX = x;
        screenY = y;

        // Initialize grid class as well as store the width and height of the grid
        grid = new Grid(context,screenX, screenY, 6, 6);
        gridWidth = grid.getGrid().length;
        gridHeight = grid.getGrid()[0].length;

        //Initialize the player class
        player = new Player(context, grid.getCenter(), grid.getPixelWidth(), grid.getPixelHeight() * 2);
    }

    public void run() {
        while (playing) {

            // Capture the current time in milliseconds in startFrameTime
            long startFrameTime = System.currentTimeMillis();

            // Update the frame
            if (!paused) {
                update();
            }

            // Draw the frame
            draw();

            // Calculate the fps this frame
            // We can then use the result to
            // time animations and more.
            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame >= 1) {
                fps = 1000 / timeThisFrame;
            }
        }
    }

    private void update() {

        // TODO Make update method for player and call here
        // TODO Make update method for grid? and call here - used for camera
    }

    private void draw(){
        // Make sure our drawing surface is valid or we crash
        if (ourHolder.getSurface().isValid()) {

            // Lock the canvas ready to draw
            canvas = ourHolder.lockCanvas();

            // Draw the background color
            canvas.drawColor(Color.argb(255, 26, 128, 182));

            //TODO Make individual for loops that branch from center pixel so player is at center of screen
            for (int i = grid.GetIndexOfPixel(grid.topLeft).x; i <= grid.GetIndexOfPixel(grid.topRight).x; i++)
            {
                for (int j = grid.GetIndexOfPixel(grid.bottomRight).y; j >= grid.GetIndexOfPixel(grid.topRight).y; j--)
                {
                    // TEMPORARY - Used to make sure Player position is correct pixel in grid - for now just make it transparent
                    if (grid.getGrid()[i][j] == player.getPosition())
                    {
                        paint.setColor(Color.argb(0,255,0,0));

                    }
                    else
                    {
                        paint.setColor(Color.argb(255,  255, 255, 255));
                    }
                    canvas.drawBitmap(grid.getGrid()[i][j].getBitmap(),grid.getGrid()[i][j].getPositionX(),grid.getGrid()[i][j].getPositionY(), paint);
                }
            }

            //TEMPORARY - Make Player semi-transparent in order to see pixels underneath player
            paint.setColor(Color.argb(100,255,255,255));
            canvas.drawBitmap(player.getBitmap(), player.getPosition().getPositionX(), player.getPosition().getPositionY() - player.getBottomMiddle(), paint);

            // Change the brush color
            paint.setColor(Color.argb(255,  249, 129, 0));
            paint.setTextSize(40);

            // Draw everything to the screen
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }

    // If Activity is paused/stopped
    // shutdown our thread.
    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }

    }

    // If Activity is started then
    // start our thread.
    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();


    }
    // The SurfaceView class implements onTouchListener
    // So we can override this method and detect screen touches.
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            // Player has touched the screen
            case MotionEvent.ACTION_DOWN:

                break;

            // Player has removed finger from screen
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }
}