package com.model;

public class ArabicTemplate extends Template {
    public ArabicTemplate() {
        super();
        this.placementSubject = "تم تحضير الطلب";
        this.shipmentSubject = "تم وصول الطلب";
    }

    @Override
    public String createPlacementContent(String userName, String productsName) {
    return "عزيزي " + userName + ",\n" +
            "تم تحضير طلبك من " + productsName + " بنجاح.\n" +
            "شكرا لاستخدامكم لخدماتنا.\n" +
            "أطيب التحيات,\n";
    }

    @Override
    public String createShipmentContent(String userName, String productsName) {
        return "عزيزي " + userName + ",\n" +
                "تم وصول طلبك من " + productsName + " بنجاح.\n" +
                "شكرا لاستخدامكم لخدماتنا.\n" +
                "أطيب التحيات,\n";
    }
}
