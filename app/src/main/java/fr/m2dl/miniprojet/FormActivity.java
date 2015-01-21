package fr.m2dl.miniprojet;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

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

    private class SpinnerListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            final List<FormCategory> finalTypeList = typeList;
            FormCategory formCategory = finalTypeList.get(position);
            if (formCategory.getFormCategoryList().size() > 0) {
                Spinner spinner = new Spinner(context);
                formLayout.addView(spinner);

                List<String> list = new ArrayList<>();
                for (FormCategory item : formCategory.getFormCategoryList()) {
                    list.add(item.toString());
                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(context,
                        android.R.layout.simple_spinner_item, list);
                spinner.setAdapter(dataAdapter);
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

        Spinner spinnerType = (Spinner) findViewById(R.id.spinnerType);
        formLayout = (LinearLayout) findViewById(R.id.formLayout);

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
        for (FormCategory t : typeList) {
            list.add(t.toString());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        spinnerType.setAdapter(dataAdapter);
        spinnerType.setOnItemSelectedListener(new SpinnerListener());

    }
}
