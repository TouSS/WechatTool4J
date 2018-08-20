package xx.wechat.tools.context;

import org.junit.Before;
import org.junit.Test;
import xx.wechat.tools.Token;
import xx.wechat.tools.bean.media.Media;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;

import java.io.File;

import static org.junit.Assert.*;

public class MediaContextTest {
    MediaContext mediaContext;
    @Before
    public void setUp() throws Exception {
        mediaContext = new MediaContext(Token.getAccessToken());
    }

    @Test
    public void addTempMedia() {
        try {
            System.out.println(mediaContext.addTempMedia(Media.MEDIA_TYPE_IMAGE, new File("C:\\Users\\Touss\\Pictures\\细川.jpg")));
        } catch (WechatException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTempMedia() {
        try {
            mediaContext.getTempMedia("2u4u698tubH8f4N4XlKHfLvO18eyUPpN40Dwffq1EZx2y4sN73cwe2GVszSGvSod", "d:/xc.jpg");
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (WechatException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTempSpeex() {
    }

    @Test
    public void addPermanentNews() {

    }

    @Test
    public void uploadNewsPic() {
    }

    @Test
    public void addPermanentMedia() {
        try {
            System.out.println(mediaContext.addPermanentMedia(Media.MEDIA_TYPE_IMAGE, new File("C:\\Users\\Touss\\Pictures\\细川.jpg"), null, null));
        } catch (WechatException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void uploadPermanentNews() {
    }

    @Test
    public void getPermanentMedia() {
        try {
            mediaContext.getPermanentMedia("1BXrP85lnQFuYJzpN7SlmNwv6TafPdo-BIHhP9DWU48", "d:/xc.jpg");
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (WechatException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void removePermanentMedia() {
    }

    @Test
    public void updatePermanentNews() {
    }

    @Test
    public void getMediaCount() {
        try {
            System.out.println(mediaContext.getMediaCount());
        } catch (WechatException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getMediaList() {
        try {
            System.out.println(mediaContext.getMediaList(Media.MEDIA_TYPE_IMAGE, 0, 20));
        } catch (WechatException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }
}