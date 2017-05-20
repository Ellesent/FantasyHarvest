package redhoundlabs.fantasyharvest

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color

/**
 * Created by cool- on 1/16/2017.
 */

class Pixel
/**
 * Constructor for Pixel
 * @param context context
 * *
 * @param positionX actual x position of pixel
 * *
 * @param positionY actual y position of pixel
 * *
 * @param length what width the pixel should be
 * *
 * @param height what height the pixel should be
 */
(context: Context, positionX: Float, positionY: Float, internal var length: Int     // width of pixel
 , internal var height: Int     // height of pixel
) {

    // Properties
    var bitmap: Bitmap? = null
        private set  //bitmap for a pixel

    var positionX: Float = 0.toFloat()
        internal set    // actual position on screen
    var positionY: Float = 0.toFloat()
        internal set


    init {

        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.pixel)

        this.positionX = positionX
        this.positionY = positionY

        // stretch the bitmap to a size appropriate for the screen resolution
        bitmap = Bitmap.createScaledBitmap(bitmap, this.length, this.height, false)

        //TODO create boolean to decide if pixel is occupied or not
        //TODO possibly create an enum to know what is occupying the pixel
    }
}