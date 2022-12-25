package com.denger.micotian.module.setting.settings;

import com.denger.micotian.module.Module;
import com.denger.micotian.module.setting.Setting;

public class FloatSetting extends Setting {
    private float min;
    private float max;
    private float val;
    public FloatSetting(String name, Module module, float min, float max, float val) {
        super(name, module);
        this.max = max;
        this.min = min;
        this.val = val;
    }

    public float getMax() {
        return max;
    }

    public float getMin() {
        return min;
    }

    public float getVal() {
        return val;
    }

    public void setVal(float val) {
        this.val = val;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public void setMin(float min) {
        this.min = min;
    }
}
