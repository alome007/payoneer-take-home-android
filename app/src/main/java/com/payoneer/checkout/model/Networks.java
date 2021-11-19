package com.payoneer.checkout.model;

import java.util.List;

public class Networks {

    private List<Applicable> applicable;

    public List<Applicable> getApplicable() {
        return applicable;
    }

    public Networks(List<Applicable> applicable) {
        this.applicable = applicable;
    }

    public void setApplicable(List<Applicable> applicable) {
        this.applicable = applicable;
    }



}
