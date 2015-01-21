package fr.m2dl.miniprojet;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by mfaure on 21/01/15.
 */
public class SendingActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending);

        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                Mail m = new Mail("biologisteM2DL@yahoo.com", "labiologie");
                String[] toArr = {"biologisteM2DL@yahoo.com"};
                m.setTo(toArr);
                m.setFrom("biologisteM2DL@yahoo.com");
                m.setSubject("This is an email sent using my Mail JavaMail wrapper from an Android device.");
                m.setBody("Email body");

                try {

                    m.addAttachment(Environment.getExternalStorageDirectory()+ "/Pic.jpg");

                    if(m.send()) {
                        Toast.makeText(getApplicationContext(), "Email was sent successfully.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Email was not sent.", Toast.LENGTH_LONG).show();
                    }
                } catch(Exception e) {
                    Log.e("MailApp", "Could not send email", e);
                }


                Intent intent = new Intent(getApplicationContext(), MainMenu.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
                return null;
            }
        };
        asyncTask.execute("");
    }
}
