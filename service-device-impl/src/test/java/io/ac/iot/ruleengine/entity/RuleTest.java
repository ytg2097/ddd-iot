//package io.ac.iot.Rule.entity;
//
//import io.ac.iot.Rule.entity.expression.Expression;
//import lombok.Data;
//import org.junit.Test;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//import java.util.stream.Collectors;
//
//import static com.google.common.collect.Lists.newArrayList;
//import static com.google.common.collect.Maps.newHashMap;
//
//public class RuleTest {
//
//    @Test
//    public void test() {
//
//        Expre eq_1 = new Expre("filed-1", Expression.Where.EQUAL, "123");
//        Expre eq_2 = new Expre("filed-2", Expression.Where.EQUAL, "123");
//        Expre eq_3 = new Expre("filed-3", Expression.Where.EQUAL, "123");
//
//        Expre and_1 = new Expre(newArrayList(eq_1, eq_2, eq_3), Expression.Conjunction.AND);
//
//        Expre ge_1 = new Expre("filed-6", Expression.Where.GREATER_EQUAL, "123");
//        Expre and_1_OR_ge_1 = new Expre(newArrayList(and_1, ge_1), Expression.Conjunction.OR);
//
//        Expre l_1 = new Expre("filed-5", Expression.Where.LESS, "129");
//
//        Expre l_1_AND_and_1_OR_ge_1 = new Expre(newArrayList(and_1_OR_ge_1, l_1), Expression.Conjunction.AND);
//        System.out.println(l_1_AND_and_1_OR_ge_1);
//
//
//        Map<String, String> matter = newHashMap();
//        matter.put("filed-1", "123");
//        matter.put("filed-2", "123");
//        matter.put("filed-3", "123");
//        matter.put("filed-6", "123");
//        matter.put("filed-5", "123");
//
//        boolean exec = l_1_AND_and_1_OR_ge_1.exec(matter);
//        System.out.println(exec);
//    }
//
//    @Data
//    class Rule {
//
//        List<Expre> expreList;
//    }
//
//    @Data
//    class Expre {
//        private String filed;
//
//        private Expression.Where where;
//
//        private String value;
//
//        public Expre(String filed, Expression.Where where, String value) {
//            this.filed = filed;
//            this.where = where;
//            this.value = value;
//        }
//
//        private List<Expre> expressions;
//
//        private Expression.Conjunction conjunction;
//
//        public Expre(List<Expre> expressions, Expression.Conjunction conjunction) {
//            this.expressions = expressions;
//            this.conjunction = conjunction;
//        }
//
//        @Override
//        public String toString() {
//
//            if (Objects.nonNull(conjunction)) {
//                return " ( " + expressions.stream().map(Expre::toString).collect(Collectors.joining(conjunction.operator)) + " ) ";
//            }
//            return filed + where.operator + value;
//        }
//
//
//
//        public boolean exec(Map<String, String> matter) {
//
//            if (Objects.nonNull(conjunction)) {
//
//                if (conjunction.equals(Expression.Conjunction.AND)) {
//                    for (Expre expression : expressions) {
//                        if (!expression.exec(matter)){
//                            return false;
//                        }
//                    }
//                } else {
//                    for (Expre expression : expressions) {
//                        if (expression.exec(matter)){
//                            return true;
//                        }
//                    }
//                }
//                return true;
//            }
//            System.out.println(filed + "----> " + matter.get(filed) + where.operator + value);
//            return comparing(matter.get(filed));
//        }
//
//        private boolean comparing(String val) {
//
//            switch (where) {
//                case EQUAL:
//                    return value.equals(val);
//                case GREATER:
//                    return Double.parseDouble(val) > Double.parseDouble(value);
//                case LESS_EQUAl:
//                    return Double.parseDouble(val) <= Double.parseDouble(value);
//                case LESS:
//                    return Double.parseDouble(val) < Double.parseDouble(value);
//                case GREATER_EQUAL:
//                    return Double.parseDouble(val) >= Double.parseDouble(value);
//                case NOT_EQUAL:
//                    return Double.parseDouble(val) != Double.parseDouble(value);
//                default:
//                    return false;
//            }
//        }
//    }
//
//
//}
