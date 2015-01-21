package fr.m2dl.miniprojet.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mfaure on 15/01/15.
 */
public class FormCategory {

    public static final FormCategory DEFAULT_CATEGORY = new FormCategory("Choose an entry");
    private String name;
    private List<FormCategory> formCategoryList;

    public FormCategory(String name) {
        this.name = name;
    }

    public FormCategory() {}

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
