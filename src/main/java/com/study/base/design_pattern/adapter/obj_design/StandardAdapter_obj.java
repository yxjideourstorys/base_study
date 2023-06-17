package com.study.base.design_pattern.adapter.obj_design;

public class StandardAdapter_obj implements BritishStandard_obj {

    private ChineseStandard_obj chineseStandard;

    public StandardAdapter_obj(ChineseStandard_obj chineseStandard){
        this.chineseStandard = chineseStandard;
    }

    @Override
    public String getBritishStandard() {
        return chineseStandard.getChineseStandard();
    }
}
