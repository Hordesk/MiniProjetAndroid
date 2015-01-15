package fr.m2dl.miniprojet;

import java.io.InputStream;
        import java.util.List;
        import javax.xml.parsers.SAXParserFactory;
        import org.xml.sax.InputSource;
        import org.xml.sax.XMLReader;
        import android.util.Log;

public class SAXXMLParser {
    public static List<String> parse(InputStream is) {
        List<String> employees = null;
        try {
            // create a XMLReader from SAXParser
            XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser()
                    .getXMLReader();
            // create a SAXXMLHandler
            SAXXMLHandler saxHandler = new SAXXMLHandler();
            // store handler in XMLReader
            xmlReader.setContentHandler(saxHandler);
            // the process starts
            xmlReader.parse(new InputSource(is));
            // get the `Employee list`
            employees = saxHandler.getEmployees();

        } catch (Exception ex) {
            Log.d("XML", "SAXXMLParser: parse() failed");
        }

        // return Employee list
        return employees;
    }
}