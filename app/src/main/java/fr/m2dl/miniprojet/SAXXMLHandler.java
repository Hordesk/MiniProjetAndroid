package fr.m2dl.miniprojet;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import fr.m2dl.miniprojet.domain.Type;

public class SAXXMLHandler extends DefaultHandler {

    private ArrayList<Type> employees;
    private String tempVal;
    private Type tempEmp;

    public SAXXMLHandler() {
        employees = new ArrayList<Type>();
    }

    public ArrayList<Type> getEmployees() {
        return employees;
    }

    // Event Handlers
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        // reset
        tempVal = "";
        if (qName.equalsIgnoreCase("employee")) {
            // create a new instance of employee
            tempEmp = new Type();
        }
    }

    public void characters(char[] ch, int start, int length)
            throws SAXException {
        tempVal = new String(ch, start, length);
    }

    //TODO populate current type
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (qName.equalsIgnoreCase("employee")) {
            // add it to the list
//            employees.add(tempEmp);
        } else if (qName.equalsIgnoreCase("id")) {
//            tempEmp.setId(Integer.parseInt(tempVal));
        } else if (qName.equalsIgnoreCase("name")) {
//            tempEmp.setName(tempVal);
        } else if (qName.equalsIgnoreCase("department")) {
//            tempEmp.setDepartment(tempVal);
        } else if (qName.equalsIgnoreCase("type")) {
//            tempEmp.setType(tempVal);
        } else if (qName.equalsIgnoreCase("email")) {
//            tempEmp.setEmail(tempVal);
        }
    }
}
