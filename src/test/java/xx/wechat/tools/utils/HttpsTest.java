package xx.wechat.tools.utils;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;

public class HttpsTest {

    @Test
    public void get() {
        try {
            System.out.println(JSON.toJSONString(Access.getAccessToken("wx1f47e3c510d330dc", "13114dfaa18f61d04666b4f19364ce2f")));
        } catch (WechatException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void post() {
    }
}