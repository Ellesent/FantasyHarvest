package redhoundlabs.fantasyharvest;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Player {

    Bitmap bitmap;
    Pixel position;

    int length;
    int height;

    float halfSprite;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Pixel getPosition() {
        return position;
    }

    public void setPosition(Pixel value) {
        this.position = value;
    }

    public float getHalfSprite() {
        return halfSprite;
    }


    public Player(Context context, Pixel startPosition, int length, int height) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
        position = startPosition;

        this.length = length;
        this.height = height;

        //halfSprite = length / 2;

        bitmap = Bitmap.createScaledBitmap(bitmap, this.length, this.height, false);

        halfSprite = bitmap.getHeight() / 2;
    }

    public void Update() {
        // TODO move player based on touch
    }
}
