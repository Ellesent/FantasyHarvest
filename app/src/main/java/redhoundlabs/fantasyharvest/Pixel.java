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
    int length;
    int height;
    public Bitmap getBitmap(){
        return bitmap;
    }

    public float getPositionX(){
        return positionX;
    }

    public float getPositionY(){
        return positionY;
    }

    public Pixel(Context context, float positionX, float positionY, int length, int height){

        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pixel);

        this.length = length;
        this.height = height;

        this.positionX = positionX;
        this.positionY = positionY;

        // stretch the bitmap to a size appropriate for the screen resolution
        bitmap = Bitmap.createScaledBitmap(bitmap, this.length, this.height, false);

    }
}