package fr.m2dl.miniprojet;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
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
                Mail m = new Mail("biologistem2dl@gmail.com", "labiologie");
                String[] toArr = {"biologistem2dl@gmail.com"};
                m.setTo(toArr);
                m.setFrom("biologistem2dl@gmail.com");
                m.setSubject("Nouvelle photo de: "+ StaticData.userName+"\n");
                if(StaticData.xCrossPos == -10){
                    m.setBody("Nom d'utilisateur: "+ StaticData.userName+"\nZone signalée: x1 = " + StaticData.xTopLeftSquare + " -- x2 = "+StaticData.xBottomRightSquare+" -- y1 = " + StaticData.yTopLeftSquare + " -- y2 = " + StaticData.yBottomRightSquare+"\nType : " +StaticData.info +"\nCoordonnées GPS : longitude = "+ StaticData.lon + " -- latitude = " +StaticData.lat+ "\nCommentaire : \n" + StaticData.commentaire);
                }else{

                    m.setBody("Nom d'utilisateur: "+ StaticData.userName+"\nPoint signalé: x = " + StaticData.xCrossPos + " -- y = "+StaticData.yCrossPos+"\nType : "+StaticData.info +"\nCoordonnées GPS : longitude = "+ StaticData.lon + " -- latitude = " +StaticData.lat+ "\nCommentaire : \n" + StaticData.commentaire);
                }

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
