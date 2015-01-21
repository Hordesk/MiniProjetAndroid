package fr.m2dl.miniprojet;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by mfaure on 21/01/15.
 */
public class DisplayPhotoActivity extends Activity {



    Button boutonValider;
    Button boutonRetour;
ImageView imageView;

    //Drawer
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_photo);
        imageView = (ImageView) findViewById(R.id.imageViewDisplayPhoto);


        String photoPath = Environment.getExternalStorageDirectory()+ "/Pic.jpg";
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(photoPath, options);

        imageView.setImageBitmap(bitmap);
    }
}
