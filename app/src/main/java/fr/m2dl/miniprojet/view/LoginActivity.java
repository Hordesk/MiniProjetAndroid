package fr.m2dl.miniprojet.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.m2dl.miniprojet.R;
import fr.m2dl.miniprojet.utils.StaticData;

/**
 * Created by mfaure on 21/01/15.
 */
public class LoginActivity extends Activity {


    private Button boutonValider;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        boutonValider = (Button) findViewById(R.id.buttonValider);
        editText = (EditText) findViewById(R.id.editText);

        boutonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "Rentrez un pseudo SVP", Toast.LENGTH_SHORT).show();
                }else{

                    StaticData.userName = editText.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), MainMenu.class);

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                }



            }
        });
    }
}
