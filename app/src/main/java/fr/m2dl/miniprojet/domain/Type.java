package fr.m2dl.miniprojet.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mfaure on 15/01/15.
 */
public class Type {

    private String name;
    private List<Detail> detailList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Detail> getDetailList() {
        return detailList;
    }

    public void addDetail(Detail detail) {
        if (detailList == null) {
            detailList = new ArrayList<>();
        }
        detailList.add(detail);
    }
}
