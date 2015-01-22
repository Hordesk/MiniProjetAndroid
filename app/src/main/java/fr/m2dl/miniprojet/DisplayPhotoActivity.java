package fr.m2dl.miniprojet;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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
        boutonRetour = (Button) findViewById(R.id.buttonRetour);


        boutonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainMenu.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });


        String photoPath = Environment.getExternalStorageDirectory()+ "/Pic.jpg";
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(photoPath, options);

        try{

            imageView.setImageBitmap(bitmap);

        }catch (Exception e){
            Toast.makeText(this, "Pas de photo existante", Toast.LENGTH_SHORT).show();
        }
    }
}
