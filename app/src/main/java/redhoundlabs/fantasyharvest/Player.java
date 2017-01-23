package redhoundlabs.fantasyharvest;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Player {

    Bitmap bitmap;
    Pixel position;

    int length;
    int height;

    float bottomMiddle;

    public Bitmap getBitmap()
    {
        return bitmap;
    }

    public Pixel getPosition()
    {
        return position;
    }

    public float getBottomMiddle() {return bottomMiddle;}

    public int getHeight(){return height;}

    public Player(Context context, Pixel startPosition, int length, int height)
    {
        bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.player);
        position = startPosition;

        this.length = length;
        this.height = height;

        bottomMiddle = length / 2;

        bitmap = Bitmap.createScaledBitmap(bitmap, this.length, this.height, false);


    }

    public void Update()
    {}


}


