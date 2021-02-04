package io.ac.iot.servicedatahub.aggregation.ruleengine.root.expression;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-12-17
 **/
public enum Where {

    EQUAL(" = "),
    GREATER(" > "),
    LESS(" < "),
    GREATER_EQUAL(" >= "),
    LESS_EQUAl(" <= "),
    NOT_EQUAL(" <> ");

    public final String operator;

    Where(String operator) {
        this.operator = operator;
    }
}
