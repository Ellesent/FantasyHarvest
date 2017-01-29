package redhoundlabs.fantasyharvest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;

/**
 * Created by cool- on 1/16/2017.
 */

public class Pixel {

    private Bitmap bitmap;  //bitmap for a pixel

    float positionX;    // actual position on screen
    float positionY;

    int length;     // width of pixel
    int height;     // height of pixel

    // Properties
    public Bitmap getBitmap(){
        return bitmap;
    }

    public float getPositionX(){
        return positionX;
    }

    public float getPositionY(){
        return positionY;
    }


    /**
     * Constructor for Pixel
     * @param context context
     * @param positionX actual x position of pixel
     * @param positionY actual y position of pixel
     * @param length what width the pixel should be
     * @param height what height the pixel should be
     */
    public Pixel(Context context, float positionX, float positionY, int length, int height){

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pixel);


        this.length = length;
        this.height = height;

        this.positionX = positionX;
        this.positionY = positionY;

        // stretch the bitmap to a size appropriate for the screen resolution
        bitmap = Bitmap.createScaledBitmap(bitmap, this.length, this.height, false);

        //TODO create boolean to decide if pixel is occupied or not
        //TODO possibly create an enum to know what is occupying the pixel
    }
}