package fr.m2dl.miniprojet.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.m2dl.miniprojet.R;

/**
 * Created by mfaure on 06/01/2015.
 */
public class MainMenu extends Activity {
    private Activity activity = this;
    private Context context = this;

    private Button buttonPhoto;
    private Button buttonGallery;
    private Button buttonCredits;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        buttonPhoto = (Button) findViewById(R.id.buttonPhoto);
        buttonGallery = (Button) findViewById(R.id.buttonGallery);
        buttonCredits = (Button) findViewById(R.id.buttonCredits);

        buttonPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PhotoActivity.class);
                context.startActivity(intent);
            }
        });

        buttonGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DisplayPhotoActivity.class);
                context.startActivity(intent);
            }
        });

        buttonCredits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,CreditActivity.class);
                context.startActivity(intent);
            }
        });

    }
}
