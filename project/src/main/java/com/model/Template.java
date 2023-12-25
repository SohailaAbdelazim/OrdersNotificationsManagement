package com.model;

public abstract class Template {
    private String placementSubject;
    private String shipmentSubject;
    public abstract String createPlacementContent(String userName, String productsName);
    public abstract String createShipmentContent(String userName, String productsName);
}
