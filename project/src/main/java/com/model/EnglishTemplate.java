package com.model;

public class EnglishTemplate extends Template {
    public EnglishTemplate() {
        super();
        this.placementSubject = "Order placement";
        this.shipmentSubject = "order shipment";
    }

    @Override
    public String createPlacementContent(String userName, String productsName) {
        return "Dear " + userName + ",\n" +
                "Your order of " + productsName + " has been placed successfully.\n" +
                "Thank you for using our services.\n" +
                "Best regards,\n";
    }

    @Override
    public String createShipmentContent(String userName, String productsName) {
        return "Dear " + userName + ",\n" +
                "Your order of " + productsName + " has been shipped successfully.\n" +
                "Thank you for using our services.\n" +
                "Best regards,\n";
    }

    @Override
    public String toString() {
        return "English Tempalte";
    }
}
