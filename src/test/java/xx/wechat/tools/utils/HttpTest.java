package xx.wechat.tools.utils;

import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import java.io.File;

public class HttpTest {

    @Test
    public void get() {
        try {
            System.out.println(Http.get("https://www.baidu.com"));
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