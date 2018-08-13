package xx.wechat.tools.utils;

import org.junit.Test;

public class HttpsTest {

    @Test
    public void get() {
        try {
            System.out.println(Https.get("https://www.baidu.com"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getStream() {
    }

    @Test
    public void post() {
    }
}