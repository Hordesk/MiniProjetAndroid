package fr.m2dl.miniprojet;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import fr.m2dl.miniprojet.domain.Detail;
import fr.m2dl.miniprojet.domain.Type;

public class SAXXMLHandler extends DefaultHandler {

    private ArrayList<Type> typeList;
    private String tmpVal;
    private Detail lastDetail;
    private Type tmpType;
    private ArrayList<Detail> currentDetailPath;
    private boolean inDetails = false;

    public SAXXMLHandler() {
        typeList = new ArrayList<>();
        currentDetailPath = new ArrayList<>();
    }

    public ArrayList<Type> getTypeList() {
        return typeList;
    }

    public void startElement(String uri,
                             String localName,
                             String qName,
                             Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("type")) {
            tmpType = new Type();
        } else if (qName.equalsIgnoreCase("detail")) {
            lastDetail = new Detail();
            currentDetailPath.add(lastDetail);
            if (currentDetailPath.size() > 1) {
                currentDetailPath.get(currentDetailPath.size()-2).addDetail(lastDetail);
            } else {
                tmpType.addDetail(lastDetail);
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
            typeList.add(tmpType);
            Log.d("", "Type name : " + tmpType.getName());
            for(Detail detail: tmpType.getDetailList()) {
                logGraph("", detail);
            }

        } else if (qName.equalsIgnoreCase("detail")) {
            currentDetailPath.remove(currentDetailPath.size()-1);
        } else if (qName.equalsIgnoreCase("name")) {
            if (inDetails) {
                lastDetail.setName(tmpVal);
            } else {
                tmpType.setName(tmpVal);
            }
        }
    }

    public void logGraph(String prefix, Detail root) {
        Log.d("", prefix + ">> " + root.getName());
        if (root.getDetailList() != null) {
            for(Detail detail: root.getDetailList()) {
                logGraph(">> ", detail);
            }
        }
    }
}
