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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import fr.m2dl.miniprojet.domain.Detail;
import fr.m2dl.miniprojet.domain.Type;

/**
 * Created by mfaure on 15/01/15.
 */
public class FormActivity extends Activity {

    private Context context;
    private Spinner spinnerType;
    private List<Type> typeList = null;
    private List<Detail> detailList = null;
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


        for(Type t : typeList){
            list.add(t.toString());


        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        spinnerType.setAdapter(dataAdapter);



        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Type t = typeList.get(position);
                Spinner s = null;
                if(t.getDetailList().size()>0){
                    s = new Spinner(context);
                    formLayout.addView(s);
                }

                detailList = t.getDetailList();
                List<String> list = new ArrayList<String>();
                for (Detail d : detailList){
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
