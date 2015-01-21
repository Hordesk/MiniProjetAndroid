package fr.m2dl.miniprojet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by mfaure on 15/01/15.
 */
public class CommentActivity extends Activity {

    Button boutonValider;
    Button boutonRetour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        boutonValider = (Button) findViewById(R.id.buttonValider);
        boutonRetour = (Button) findViewById(R.id.buttonRetour);




        boutonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), FormActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });

        boutonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(), FormActivity.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);

            }
        });
    }

    public void generateXmlData() {

    }

    public void sendData(/*String xmlData*/) {

    }
}
