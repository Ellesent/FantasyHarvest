package redhoundlabs.fantasyharvest

/*
Main class
 */
import android.app.ActionBar
import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.Display
import android.view.View
import android.view.Window

import java.lang.reflect.Method

// the entry point to the game.
// It will handle the lifecycle of the game by calling
// methods of gameView when prompted to so by the OS.
class Game : Activity() {

    // gameView will be the view of the game
    // It will also hold the logic of the game
    // and respond to screen touches as well
    internal lateinit var gameView: GameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get a Display object to access screen details
        val display = windowManager.defaultDisplay
        var realWidth: Int
        var realHeight: Int

        if (Build.VERSION.SDK_INT >= 17) {
            //new pleasant way to get real metrics
            val realMetrics = DisplayMetrics()
            display.getRealMetrics(realMetrics)
            realWidth = realMetrics.widthPixels
            realHeight = realMetrics.heightPixels

        } else if (Build.VERSION.SDK_INT >= 14) {
            //reflection for this weird in-between time
            try {
                val mGetRawH = Display::class.java!!.getMethod("getRawHeight")
                val mGetRawW = Display::class.java!!.getMethod("getRawWidth")
                realWidth = mGetRawW.invoke(display) as Int
                realHeight = mGetRawH.invoke(display) as Int
            } catch (e: Exception) {
                //this may not be 100% accurate, but it's all we've got
                realWidth = display.width
                realHeight = display.height
                Log.e("Display Info", "Couldn't use reflection to get the real display metrics.")
            }

        } else {
            //This should be close, as lower API devices should not have window navigation bars

            val size = Point()
            display.getSize(size)
            realWidth = size.x
            realHeight = size.y
        }
        // Load the resolution into a Point object
        val size = Point()
        display.getSize(size)

        // Initialize gameView and set it as the view
        gameView = GameView(this, realWidth, realHeight)
        setContentView(gameView)

        //gameView.setSystemUiVisibility();
        gameView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

    }

    // This method executes when the player starts the game
    override fun onResume() {
        super.onResume()

        // Tell the gameView resume method to execute
        gameView.resume()
    }

    // This method executes when the player quits the game
    override fun onPause() {
        super.onPause()

        // Tell the gameView pause method to execute
        gameView.pause()
    }
}