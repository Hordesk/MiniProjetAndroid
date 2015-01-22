package fr.m2dl.miniprojet.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import fr.m2dl.miniprojet.R;
import fr.m2dl.miniprojet.utils.StaticData;

/**
 * Created by mfaure on 15/01/15.
 */
public class CommentActivity extends Activity {

    Button boutonValider;
    Button boutonRetour;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        boutonValider = (Button) findViewById(R.id.buttonValider);
        boutonRetour = (Button) findViewById(R.id.buttonRetour);
        editText = (EditText) findViewById(R.id.editText);




        boutonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    StaticData.commentaire = editText.getText().toString();

                Intent intent = new Intent(getApplicationContext(), SendingActivity.class);

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
