package com.study.base.design_pattern.adapter.interface_design;

public class AdapterTest_interface {

    public static void main(String[] args) {
        StandardAdapter_interface standardAdapter = new StandardAdapter_interface(){
            @Override
            public String getBritishStandard(){
                return new ChineseStandard_interface().getChineseStandard();
            }
        };
        System.out.println(standardAdapter.getBritishStandard());
    }

}
