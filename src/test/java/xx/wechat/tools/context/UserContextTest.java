package xx.wechat.tools.context;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import xx.wechat.tools.bean.AccessToken;
import xx.wechat.tools.bean.user.User;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserContextTest {
    UserContext userContext;

    @Before
    public void setUp() throws Exception {
        AccessToken accessToken = JSON.parseObject("{\"access_token\":\"12_jVSNXK1EJwfcBlkA-gfbLoBXW3gsGHro2fY9SkUza2q15GYpirgTpyfH1n8TRa-f0LyzLjhr6obL-1LIFIsyCgtoLNFnlbkbPUhZitrxPbmitjE_a9wiZ78dx6Sh8ykiBk0XpG0Y9oWaOR1WTZMjAIAJPC\",\"apply_timestamp\":1534492930,\"expired\":false,\"expires_in\":7200}", AccessToken.class);
        userContext = new UserContext(accessToken);
    }

    @Test
    public void getUser() {
        try {
            System.out.println(JSON.toJSONString(userContext.getUser("onkEz1u7V6dw0KvwhAp96UJI3XF0", User.LANGUAGE_ZH_CN)));
        } catch (WechatException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getUsers() {

    }

    @Test
    public void getUserList() {
        try {
            System.out.println(JSON.toJSONString(userContext.getUserList(null)));
        } catch (WechatException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void setUserRemark() {
    }

    @Test
    public void createTag() {
        try {
            System.out.println(JSON.toJSONString(userContext.createTag("TAG01")));
        } catch (WechatException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTags() {
        try {
            System.out.println(JSON.toJSONString(userContext.getTags()));
        } catch (WechatException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateTag() {
    }

    @Test
    public void removeTag() {
    }

    @Test
    public void getUsersByTag() {
        try {
            System.out.println(JSON.toJSONString(userContext.getUsersByTag(100, null)));
        } catch (WechatException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void tagUsers() {
        try {
            List<String> users = new ArrayList<>();
            users.add("onkEz1u7V6dw0KvwhAp96UJI3XF0");
            userContext.tagUsers(users, 100);
        } catch (WechatException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void untagUsers() {
    }

    @Test
    public void getUserTags() {
        try {
            System.out.println(JSON.toJSONString(userContext.getUserTags("onkEz1u7V6dw0KvwhAp96UJI3XF0")));
        } catch (WechatException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getBlackList() {
    }

    @Test
    public void blackUsers() {
    }

    @Test
    public void unblackUsers() {
    }
}