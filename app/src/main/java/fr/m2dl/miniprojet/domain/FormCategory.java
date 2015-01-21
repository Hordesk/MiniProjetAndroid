package fr.m2dl.miniprojet.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mfaure on 15/01/15.
 */
public class FormCategory {

    private String name;
    private List<FormCategory> formCategoryList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FormCategory> getFormCategoryList() {
        return formCategoryList;
    }

    public void addDetail(FormCategory formCategory) {
        if (formCategoryList == null) {
            formCategoryList = new ArrayList<>();
        }
        formCategoryList.add(formCategory);
    }
    @Override
    public String toString(){
        return name;
    }
}
