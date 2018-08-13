package xx.wechat.tools.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import xx.wechat.tools.exception.WechatException;

/**
 * 返回结果验证工具
 */
public class ResultCheck {

    /**
     * 验证微信端口调用返回结果
     *
     * @param result 微信返回内容
     * @throws WechatException 调用错误异常
     */
    public static void checkWechatResult(String result) throws WechatException {
        try {
            checkWechatResult(JSON.parseObject(result));
        } catch (JSONException e) {
            //非JSON格式数据不做处理
        }

    }

    public static void checkWechatResult(JSONObject resultObject) throws WechatException {
        //没有code的返回数据不做处理
        if (resultObject.containsKey("errcode") && resultObject.getInteger("errcode") != 0) {
            throw new WechatException(resultObject.getInteger("errcode"), resultObject.getString("errmsg"));
        }
    }
}
