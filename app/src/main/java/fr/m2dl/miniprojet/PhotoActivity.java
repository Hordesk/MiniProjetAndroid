package fr.m2dl.miniprojet;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by mfaure on 15/01/15.
 */
public class PhotoActivity extends Activity {
    private DrawableImageView imageView;
    private Uri imageUri;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Bitmap bitmap;
    
    private Button boutonValider;
    private Button boutonRetour;

    private GpsProvider gpsProvider;
    //Drawer
    private String[] mPlanetTitles = new String[3];


    private DrawerLayout drawer;
    private ExpandableListView drawerList;
    ArrayList<String> groupItem = new ArrayList<String>();
    ArrayList<Object> childItem = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_photo);
        StaticData.context = this;
        StaticData.photoActivity = this;
        imageView = (DrawableImageView) findViewById(R.id.imageViewPhoto);

        boutonValider = (Button) findViewById(R.id.buttonValider);
        boutonRetour = (Button) findViewById(R.id.buttonRetour);

        gpsProvider = new GpsProvider(this);

        boutonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (StaticData.tool == ToolStatus.NONE){
                    Toast.makeText(getApplicationContext(), "Mettez une marque SVP", Toast.LENGTH_SHORT).show();
                }else{

                    Intent intent = new Intent(getApplicationContext(), FormActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                }
            }
        });

        boutonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), MainMenu.class);

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);

            }
        });

        takePhoto();

        mPlanetTitles[0] = "Outils";
        mPlanetTitles[1] = "Annuler";
        mPlanetTitles[2] = "Valider"; //TODO use resource array //getResources().getStringArray(R.array.planets_array);





        setGroupData();
        setChildGroupData();


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawerList = (ExpandableListView) findViewById(R.id.left_drawer);

        drawerList.setAdapter(new NewAdapter(this, groupItem, childItem));

        drawerList.setOnChildClickListener(new DrawerItemClickListener());

        imageView.setOnTouchListener(new View.OnTouchListener() {

            Paint paint = new Paint();


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (StaticData.tool) {
                    case NONE:
                        Toast.makeText(getApplicationContext(), "Aucun outil sélectionné", Toast.LENGTH_SHORT).show();
                        break;
                    case CROSS:
                        if(!StaticData.markerOn){
                            float x = event.getX();
                            float y = event.getY();
                            imageView.drawPoint(x, y);
                            StaticData.xCrossPos = x;
                            StaticData.yCrossPos = y;
                        }

                        break;
                    case SQUARE:
                        if (StaticData.xTopLeftSquare < 0 || StaticData.yTopLeftSquare < 0) {
                            StaticData.xTopLeftSquare = event.getX();
                            StaticData.yTopLeftSquare = event.getY();
                        } else {
                            StaticData.xBottomRightSquare = event.getX();
                            StaticData.yBottomRightSquare = event.getY();

                            imageView.drawRect(
                                    StaticData.xTopLeftSquare, StaticData.yTopLeftSquare,
                                    StaticData.xBottomRightSquare, StaticData.yBottomRightSquare
                            );
                        }
                        break;
                }

            return true;
            }
        });
    }

    public DrawableImageView getImageView() {
        return imageView;
    }

    public void takePhoto() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");

        File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);

        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            //Si l'activité était une prise de photo
            case CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    Uri selectedImage = imageUri;
                    getContentResolver().notifyChange(selectedImage, null);
                    ContentResolver cr = getContentResolver();
                    bitmap = null;
                    try {
                        bitmap = android.provider.MediaStore.Images.Media
                                .getBitmap(cr, selectedImage);
                        imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 4, bitmap.getHeight() / 4, false));
                        //Affichage de l'infobulle
                        Toast.makeText(this, selectedImage.toString(),
                                Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(this, "Failed to load", Toast.LENGTH_SHORT)
                                .show();
                        Log.e("Camera", e.toString());
                    }

                }else{
                    Intent intent = new Intent(getApplicationContext(), MainMenu.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(intent);
                    finishActivity(0);
                }
        }


        if (gpsProvider.isEnabled()) {
                StaticData.lat =  gpsProvider.getLatitude() ;
                StaticData.lon =  gpsProvider.getLongitude();
                Log.d("gps",StaticData.lon+"--"+StaticData.lat);
        } else {
            Toast.makeText(getApplicationContext(), "Le gps n'est pas allumé.", Toast.LENGTH_SHORT ).show();
            takePhoto();
        }
        gpsProvider.destroy();



}

    public void setGroupData() {
        groupItem.add("Outils");
        groupItem.add("Effacer marqueur");
        groupItem.add("Valider");
        groupItem.add("Retour caméra");
    }

    public void setChildGroupData() {
        ArrayList<String> child = new ArrayList<>();
        child.add("Point");
        child.add("Zone");
        childItem.add(child);

        childItem.add(new ArrayList<String>());
        childItem.add(new ArrayList<String>());
        childItem.add(new ArrayList<String>());
    }



    private class DrawerItemClickListener implements ExpandableListView.OnChildClickListener {

        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

            switch (groupPosition){
                case 0:
                    switch (childPosition){
                        case 0:
                            StaticData.tool = ToolStatus.CROSS;
                            StaticData.xCrossPos = -10;
                            StaticData.yCrossPos = -10;
                            StaticData.xBottomRightSquare = -10;
                            StaticData.xTopLeftSquare = -10;
                            StaticData.yBottomRightSquare = -10;
                            StaticData.yTopLeftSquare = -10;
                            break;
                        case 1:
                            StaticData.tool = ToolStatus.SQUARE;
                            StaticData.xCrossPos = -10;
                            StaticData.yCrossPos = -10;
                            StaticData.xBottomRightSquare = -10;
                            StaticData.xTopLeftSquare = -10;
                            StaticData.yBottomRightSquare = -10;
                            StaticData.yTopLeftSquare = -10;
                            break;

                    }
                    break;

                case 1:

                    break;



            }
            return true;
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        getActionBar().setTitle(title);
    }
}
