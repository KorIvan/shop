package com.tsystems.model;
/**
 * When Client is purchasing Order, he can choose Delivery Method. There are 3 options:
 * 1)self delivery;\n
 * 2)courier delivery;\n
 * 3)other (services such as POST,FEDEX,UPS,DHL).
 * @author Ivan Kornelyuk
 *
 */
public enum DeliveryMethod {
SELF_DELIVERY,COURIER, OTHER,UNKNOWN
//POST,FEDEX,UPS,DHL
}
