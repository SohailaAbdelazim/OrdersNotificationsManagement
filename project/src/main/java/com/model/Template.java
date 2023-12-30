package com.model;

public abstract class Template {
    protected String placementSubject;
    protected String shipmentSubject;

    public abstract String createPlacementContent(String userName, String productsName);

    public abstract String createShipmentContent(String userName, String productsName);

    public String getPlacementSubject() {
        return placementSubject;
    }

    public String getShipmentSubject() {
        return shipmentSubject;
    }
}
