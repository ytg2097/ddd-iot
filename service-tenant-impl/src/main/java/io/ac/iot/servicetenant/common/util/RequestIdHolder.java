package io.ac.iot.servicetenant.common.util;

import io.ac.starter.util.RequestHeadHolder;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-23
 **/
public class RequestIdHolder {

    public final static Holder opId = new Holder(RequestHeadHolder.RequestHead.OPERATOR_ID);
    public final static Holder tid = new Holder(RequestHeadHolder.RequestHead.TENANT_ID);

    public static class Holder{

        private final RequestHeadHolder.RequestHead head;

        public Holder(RequestHeadHolder.RequestHead head) {
            this.head = head;
        }

        public String get(){

            return RequestHeadHolder.get(head);
        }

        public String find(){

            return RequestHeadHolder.find(head);
        }
    }
}
