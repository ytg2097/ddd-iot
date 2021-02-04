package io.ac.iot.servicedatahub.aggregation.ruleengine.root.expression;

import io.ac.iot.servicedatahub.common.domain.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-12
 **/
@Data
@Entity
@Table(name = "`expression`")
public class Expression extends BaseEntity {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "`field`")
    private String field;

    @Column(name = "`where`")
    @Enumerated(EnumType.STRING)
    private Where where;

    private String value;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(
            name = "bracket_id"
    )
    private List<Expression> expressions;

    @Enumerated(EnumType.STRING)
    private Conjunction conjunction;


    @Override
    public String toString() {

        if (Objects.nonNull(conjunction)) {
            return "(" + expressions.stream().map(Expression::toString).collect(Collectors.joining(conjunction.operator)) + ")";
        }
        return field + where.operator + value;
    }

    public boolean filter(Map<String, String> matter) {

        if (Objects.nonNull(conjunction)) {

            if (conjunction.equals(Conjunction.AND)) {
                for (Expression expression : expressions) {
                    if (!expression.filter(matter)){
                        return false;
                    }
                }
            } else {
                for (Expression expression : expressions) {
                    if (expression.filter(matter)){
                        return true;
                    }
                }
            }
            return true;
        }
        System.out.println(field + "----> " + matter.get(field) + where.operator + value);
        return comparing(matter.get(field));
    }

    private boolean comparing(String val) {

        switch (where) {
            case EQUAL:
                return value.equals(val);
            case GREATER:
                return Double.parseDouble(val) > Double.parseDouble(value);
            case LESS_EQUAl:
                return Double.parseDouble(val) <= Double.parseDouble(value);
            case LESS:
                return Double.parseDouble(val) < Double.parseDouble(value);
            case GREATER_EQUAL:
                return Double.parseDouble(val) >= Double.parseDouble(value);
            case NOT_EQUAL:
                return Double.parseDouble(val) != Double.parseDouble(value);
            default:
                return false;
        }
    }
}
