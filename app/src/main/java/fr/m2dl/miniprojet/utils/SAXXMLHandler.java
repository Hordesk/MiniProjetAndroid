package fr.m2dl.miniprojet.utils;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

import fr.m2dl.miniprojet.domain.FormCategory;

public class SAXXMLHandler extends DefaultHandler {

    private ArrayList<FormCategory> categoryList;
    private String tmpVal;
    private FormCategory lastFormCategory;
    private FormCategory rootCategory;
    private ArrayList<FormCategory> currentFormCategoryPath;
    private boolean inDetails = false;

    public SAXXMLHandler() {
        categoryList = new ArrayList<>();
        currentFormCategoryPath = new ArrayList<>();
    }

    public ArrayList<FormCategory> getCategoryList() {
        return categoryList;
    }

    public void startElement(String uri,
                             String localName,
                             String qName,
                             Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("type")) {
            rootCategory = new FormCategory();
        } else if (qName.equalsIgnoreCase("detail")) {
            lastFormCategory = new FormCategory();
            currentFormCategoryPath.add(lastFormCategory);
            if (currentFormCategoryPath.size() > 1) {
                currentFormCategoryPath.get(currentFormCategoryPath.size()-2).addDetail(lastFormCategory);
            } else {
                rootCategory.addDetail(lastFormCategory);
            }
        } else if (qName.equalsIgnoreCase("details")) {
            inDetails = true;
        }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        tmpVal = new String(ch, start, length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("type")) {
            inDetails = false;
            categoryList.add(rootCategory);
            Log.d("", "Type name : " + rootCategory.getName());
            for(FormCategory formCategory : rootCategory.getFormCategoryList()) {
                logGraph("", formCategory);
            }

        } else if (qName.equalsIgnoreCase("detail")) {
            currentFormCategoryPath.remove(currentFormCategoryPath.size()-1);
        } else if (qName.equalsIgnoreCase("name")) {
            if (inDetails) {
                lastFormCategory.setName(tmpVal);
            } else {
                rootCategory.setName(tmpVal);
            }
        }
    }

    public void logGraph(String prefix, FormCategory root) {
        Log.d("", prefix + ">> " + root.getName());
        if (root.getFormCategoryList() != null) {
            for(FormCategory formCategory : root.getFormCategoryList()) {
                logGraph(">> ", formCategory);
            }
        }
    }
}
