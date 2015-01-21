package fr.m2dl.miniprojet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import fr.m2dl.miniprojet.domain.FormCategory;

/**
 * Created by mfaure on 15/01/15.
 */
public class FormActivity extends Activity {

    private Context context;
    private LinearLayout formLayout;
    List<FormCategory> typeList;
    List<Spinner> spinnerList;
    Button boutonValider;
    Button boutonRetour;

    private class SpinnerListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Spinner selectedSpinner = (Spinner) parent;
            TextView textView = (TextView) view;
            if (!textView.getText().equals(FormCategory.DEFAULT_CATEGORY.toString())) {
                int depth = spinnerList.indexOf(selectedSpinner);
                if (depth < spinnerList.size() - 1) {
                    for(int it = depth + 1; it < spinnerList.size(); it++) {
                        formLayout.removeView(spinnerList.get(it));
                    }
                    spinnerList.subList(depth + 1, spinnerList.size()).clear(); // Removing from the next spinner to the end of the list
                }

                FormCategory formCategory = typeList.get(spinnerList.get(0).getSelectedItemPosition() - 1);
                int tmpDepth = depth;
                while (tmpDepth > 0) {
                    formCategory = formCategory.getFormCategoryList().get(
                            spinnerList.get(depth - tmpDepth + 1).getSelectedItemPosition() - 1
                    );
                    tmpDepth--;
                }

                if (formCategory.getFormCategoryList() != null) {
                    List<String> list = new ArrayList<>();
                    list.add(FormCategory.DEFAULT_CATEGORY.toString());
                    for (FormCategory item : formCategory.getFormCategoryList()) {
                        list.add(item.toString());
                    }

                    Spinner newSpinner = new Spinner(context);
                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context,
                            android.R.layout.simple_spinner_dropdown_item, list);
                    newSpinner.setAdapter(dataAdapter);
                    newSpinner.setOnItemSelectedListener(new SpinnerListener());
                    newSpinner.setDrawingCacheBackgroundColor(Color.WHITE);
                    formLayout.addView(newSpinner);
                    spinnerList.add(newSpinner);
                }
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        this.context = this;

        formLayout = (LinearLayout) findViewById(R.id.formLayout);
        boutonValider = (Button) findViewById(R.id.buttonValider);
        boutonRetour = (Button) findViewById(R.id.buttonRetour);

        boutonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, CommentActivity.class);
                context.startActivity(intent);
            }
        });

        boutonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, PhotoActivity.class);
                context.startActivity(intent);
            }
        });

        spinnerList = new ArrayList<>();
        Spinner spinnerType = (Spinner) findViewById(R.id.spinnerType);
        spinnerList.add(spinnerType);

        List<String> list = new ArrayList<>();
        typeList = null;

        try {
            AssetManager manager = getAssets();
            InputStream stream;
            stream = manager.open("formCategories.xml");
            typeList = SAXXMLParser.parse(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (typeList == null) {
            //TODO Toast 'no xml found' and return main menu.
        }

        list.add(FormCategory.DEFAULT_CATEGORY.toString());
        for (FormCategory formCategory : typeList) {
            list.add(formCategory.toString());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, list);
        spinnerType.setAdapter(dataAdapter);
        spinnerType.setOnItemSelectedListener(new SpinnerListener());
    }
}
