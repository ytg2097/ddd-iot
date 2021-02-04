package io.ac.iot.servicelog;

import com.alibaba.fastjson.JSONObject;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-30
 **/
public abstract class AbstractJsonStringArgs {

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
