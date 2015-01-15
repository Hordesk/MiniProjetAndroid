package fr.m2dl.miniprojet;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by mfaure on 15/01/15.
 */
public class PhotoActivity extends Activity {
    private ImageView imageView;
    private Uri imageUri;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private Bitmap bitmap;



    //Drawer
    private String[] mPlanetTitles = new String[3];
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;


    private DrawerLayout drawer;
    private ExpandableListView drawerList;
    ArrayList<String> groupItem = new ArrayList<String>();
    ArrayList<Object> childItem = new ArrayList<Object>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_photo);
        imageView = (ImageView) findViewById(R.id.imageViewPhoto);
        takePhoto();

        mPlanetTitles[0] = "Outils";
        mPlanetTitles[1] = "Annuler";
        mPlanetTitles[2] = "Valider"; //TODO //getResources().getStringArray(R.array.planets_array);
//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
//        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
//                R.layout.drawer_list_item, mPlanetTitles));
        // Set the list's click listener
//        mDrawerList.setOnClickListener(new DrawerItemClickListener());


        setGroupData();
        setChildGroupData();


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawerList = (ExpandableListView) findViewById(R.id.left_drawer);

        drawerList.setAdapter(new NewAdapter(this, groupItem, childItem));

        drawerList.setOnChildClickListener(new DrawerItemClickListener());
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

                    new Thread(new Runnable() {
                        @Override
                        public void run() {


                            PhotoActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                }
                            });
                        }
                    }).start();

                }
        }

    }



    public void setGroupData() {
        groupItem.add("TechNology");
        groupItem.add("Mobile");
        groupItem.add("Manufacturer");
        groupItem.add("Extras");
    }

    public void setChildGroupData() {
        /**
         * Add Data For TecthNology
         */
        ArrayList<String> child = new ArrayList<String>();
        child.add("Java");
        child.add("Drupal");
        child.add(".Net Framework");
        child.add("PHP");
        childItem.add(child);

        /**
         * Add Data For Mobile
         */
        child = new ArrayList<String>();
        child.add("Android");
        child.add("Window Mobile");
        child.add("iPHone");
        child.add("Blackberry");
        childItem.add(child);
        /**
         * Add Data For Manufacture
         */
        child = new ArrayList<String>();
        child.add("HTC");
        child.add("Apple");
        child.add("Samsung");
        child.add("Nokia");
        childItem.add(child);
        /**
         * Add Data For Extras
         */
        child = new ArrayList<String>();
        child.add("Contact Us");
        child.add("About Us");
        child.add("Location");
        child.add("Root Cause");
        childItem.add(child);
    }



    private class DrawerItemClickListener implements ExpandableListView.OnChildClickListener {

        private DrawerLayout drawer;
        private ExpandableListView drawerList;
        private ActionBarDrawerToggle actionBarDrawerToggle;


        private void initDrawer() {
            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

            drawerList = (ExpandableListView) findViewById(R.id.left_drawer);

            drawerList.setAdapter(new NewAdapter(getApplicationContext(), groupItem, childItem));

            drawerList.setOnChildClickListener(this);

            // actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer,
            // R.drawable.ic_drawer, R.string.open_drawer,
            // R.string.close_drawer) {
            // public void onDrawerClosed(View view) {
            // getActionBar().setSubtitle("open");
            // }
            //
            // /** Called when a drawer has settled in a completely open state. */
            // public void onDrawerOpened(View drawerView) {
            // getActionBar().setSubtitle("close");
            // }
            //
            // };
            //
            // drawer.setDrawerListener(actionBarDrawerToggle);

        }


        @Override
        public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
            return true;
        }


    }

    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
//        // Create a new fragment and specify the planet to show based on position
//        Bundle args = new Bundle();
//        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
//        fragment.setArguments(args);
//
//        // Insert the fragment by replacing any existing fragment
//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.content_frame, fragment)
//                .commit();
//
//        // Highlight the selected item, update the title, and close the drawer
//        mDrawerList.setItemChecked(position, true);
//        setTitle(mPlanetTitles[position]);
//        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
//        mTitle = title;
        getActionBar().setTitle(title);
    }
}
