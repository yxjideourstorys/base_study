package com.study.base.design_pattern.adapter.class_design;

/**
 * 适配器模式
 */
public class StandardClassClassAdapter_class extends ChineseStandard_class implements BritishStandard_class {

    @Override
    public String getBritishStandard() {
        return this.getChineseStandard();
    }
}
