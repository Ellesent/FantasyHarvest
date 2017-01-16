package redhoundlabs.fantasyharvest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by cool- on 1/16/2017.
 */

public class Pixel {

    private Bitmap bitmap;
    float positionX;
    float positionY;

    public Bitmap getBitmap(){
        return bitmap;
    }

    public float getPositionX(){
        return positionX;
    }

    public float getPositionY(){
        return positionY;
    }

    public Pixel(Context context, float positionX, float positionY){

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pixel);

        int length = 32;
        int height = 32;

        this.positionX = positionX;
        this.positionY = positionY;

        // stretch the bitmap to a size appropriate for the screen resolution
        bitmap = Bitmap.createScaledBitmap(bitmap, length, height, false);

    }
}