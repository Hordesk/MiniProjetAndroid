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
    private Spinner spinnerType;
    private List<FormCategory> typeList = null;
    private List<FormCategory> formCategoryList = null;
    private Spinner spinnerDetails;
    private LinearLayout formLayout;
    private String NODE_EMP = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        this.context = this;

        spinnerType = (Spinner) findViewById(R.id.spinnerType);
        formLayout = (LinearLayout) findViewById(R.id.formLayout);

        List<String> list = new ArrayList<String>();


        try {
            AssetManager manager = getAssets();
            InputStream stream;
            stream = manager.open("cleCarac.xml");
            typeList = SAXXMLParser.parse(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }


        for(FormCategory t : typeList){
            list.add(t.toString());


        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        spinnerType.setAdapter(dataAdapter);



        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                FormCategory t = typeList.get(position);
                Spinner s = null;
                if(t.getFormCategoryList().size()>0){
                    s = new Spinner(context);
                    formLayout.addView(s);
                }

                formCategoryList = t.getFormCategoryList();
                List<String> list = new ArrayList<String>();
                for (FormCategory d : formCategoryList){
                    list.add(d.toString());
                }
                ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
                        android.R.layout.simple_spinner_item, list);
                s.setAdapter(dataAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
