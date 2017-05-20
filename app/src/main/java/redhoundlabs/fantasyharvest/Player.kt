package redhoundlabs.fantasyharvest


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Player(context: Context, var position: Pixel, internal var length: Int, internal var height: Int) {

    var bitmap: Bitmap
        internal set

    var halfSprite: Float = 0.toFloat()
        internal set


    init {
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.player)

        //halfSprite = length / 2;

        bitmap = Bitmap.createScaledBitmap(bitmap, this.length, this.height, false)

        halfSprite = (bitmap.height / 2).toFloat()
    }

    fun Update() {
        // TODO move player based on touch
    }
}
