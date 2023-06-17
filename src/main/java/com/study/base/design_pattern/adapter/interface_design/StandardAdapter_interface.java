package com.study.base.design_pattern.adapter.interface_design;

public abstract class StandardAdapter_interface extends ChineseStandard_interface implements BritishStandard_interface {
    @Override
    public String getBritishStandard() {
        return null;
    }

    @Override
    public String getTypeC() {
        return null;
    }

    @Override
    public String getUsb() {
        return null;
    }
}
