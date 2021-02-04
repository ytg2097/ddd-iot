package io.ac.iot.ruleengine.event;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-18
 **/
public class RuleEngineEngineEventEnum {

    /**
     * 规则新增
     */
    public static final String RULE_CREATED = "RULE_CREATED";

    /**
     * 规则新增
     */
    public static final String RULE_DELETED = "RULE_DELETED";

    /**
     * 规则修改
     */
    public static final String RULE_MODIFIED = "RULE_MODIFIED";

    /**
     * 状态修改
     */
    public static final String RULE_STATE_CHANGED = "RULE_STATE_CHANGED";

    /**
     * 报警规则修改
     */
    public static final String ALARM_RULE_MODIFYED = "ALARM_RULE_MODIFYED";

    /**
     * 命令动作规则修改
     */
    public static final String ACTION_RULE_MODIFYED = "ACTION_RULE_MODIFYED";

    /**
     * 数据转发规则修改
     */
    public static final String FORWARD_RULE_MODIFYED = "FORWARD_RULE_MODIFYED";
}
