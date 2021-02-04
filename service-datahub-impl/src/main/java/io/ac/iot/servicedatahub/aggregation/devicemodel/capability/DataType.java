package io.ac.iot.servicedatahub.aggregation.devicemodel.capability;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-03
 **/
public enum DataType {

    INT("int"),
    STRING("String"),
    LIST("List"),
    DECIMAL("Decimal"),
    DATETIME("DateTime"),
    JSONOBJECT("JsonObject");


    DataType(String type) {
        this.type = type;
    }

    private String type;

    public String getType() {
        return type;
    }}
