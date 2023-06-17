package com.study.base.design_pattern.adapter.obj_design;

public class AdapterTest {

    public static void main(String[] args) {
        StandardAdapter_obj standardAdapter = new StandardAdapter_obj(new ChineseStandard_obj());
        System.out.println(standardAdapter.getBritishStandard());
    }
}
