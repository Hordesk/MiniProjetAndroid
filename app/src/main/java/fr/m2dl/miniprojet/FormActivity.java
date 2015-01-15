package fr.m2dl.miniprojet;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mfaure on 15/01/15.
 */
public class FormActivity extends Activity {

    private Spinner spinnerType;
    private Spinner spinnerDetails;
    private String NODE_EMP = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        spinnerType = (Spinner) findViewById(R.id.spinnerType);
        spinnerDetails = (Spinner) findViewById(R.id.spinnerDetail);


        List<String> list = new ArrayList<String>();
        list.add("Animal");
        list.add("Végétal");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(dataAdapter);


        XMLDomParser parser = new XMLDomParser();
        AssetManager manager = getAssets();
        InputStream stream;
        try {
            stream = manager.open("employee.xml");
            Document doc = parser.getDocument(stream);

            // Get elements by name employee
            NodeList nodeList = doc.getElementsByTagName(NODE_EMP);

            /*
             * for each <employee> element get text of name, salary and
             * designation
             */
            // Here, we have only one <employee> element
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element e = (Element) nodeList.item(i);
                nameText.setText(parser.getValue(e, NODE_NAME));
                salaryText.setText(parser.getValue(e, NODE_SALARY));
                designationText.setText(parser.getValue(e, NODE_DESIGNATION));
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
