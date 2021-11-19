package com.payoneer.checkout.model;

import java.util.List;

public class LinkResults {
    public Networks getNetworks() {
        return networks;
    }

    public void setNetworks(Networks networks) {
        this.networks = networks;
    }

    public LinkResults(Networks networks) {
        this.networks = networks;
    }

    private Networks networks;

}
