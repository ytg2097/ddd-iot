package io.ac.iot.servicedatahub.aggregation.ruleengine.root.expression;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-12-17
 **/
public enum Conjunction {

    AND(" AND "),
    OR(" OR ");

    public final String operator;

    Conjunction(String operator) {
        this.operator = operator;
    }
}
