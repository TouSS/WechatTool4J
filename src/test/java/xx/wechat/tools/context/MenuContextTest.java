package xx.wechat.tools.context;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import xx.wechat.tools.Token;
import xx.wechat.tools.bean.AccessToken;
import xx.wechat.tools.bean.menu.Button;
import xx.wechat.tools.bean.menu.ConditionalMenu;
import xx.wechat.tools.bean.menu.MatchRule;
import xx.wechat.tools.bean.menu.Menu;
import xx.wechat.tools.bean.user.User;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class MenuContextTest {
    MenuContext menuContext;

    @Before
    public void setUp() throws Exception {
        AccessToken accessToken = Token.getAccessToken();
        menuContext = new MenuContext(accessToken);
    }

    @Test
    public void createMenu() {
        try {
            Menu menu = new Menu();
            List<Button> buttons = new ArrayList<>();
            Button button = new Button();
            button.setName("京东");
            button.setType(Button.BUTTON_TYPE_VIEW);
            button.setUrl("https://www.jd.com");
            buttons.add(button);

            button = new Button();
            button.setName("A0A");
            button.setType(Button.BUTTON_TYPE_VIEW);
            button.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1f47e3c510d330dc&redirect_uri="+URLEncoder.encode("http://62214a01.ngrok.io/auth-1/index.html", "UTF-8")+"&response_type=code&scope=snsapi_userinfo&state=ToolTes#wechat_redirect");
            buttons.add(button);

            button = new Button();
            button.setName("亚马逊");
            button.setType(Button.BUTTON_TYPE_VIEW);
            button.setUrl("https://www.amazon.com");
            buttons.add(button);

            menu.setButtons(buttons);

            menuContext.createMenu(menu);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addConditionalMenu() {
        try {
            ConditionalMenu conditionalMenu = new ConditionalMenu();

            List<Button> buttons = new ArrayList<>();
            Button button = new Button();
            button.setName("京东");
            button.setType(Button.BUTTON_TYPE_VIEW);
            button.setUrl("https://www.jd.com");
            buttons.add(button);

            button = new Button();
            button.setName("A0A");
            button.setType(Button.BUTTON_TYPE_VIEW);

            button.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx1f47e3c510d330dc&redirect_uri="+URLEncoder.encode("http://62214a01.ngrok.io/auth-1/index.html", "UTF-8")+"&response_type=code&scope=snsapi_userinfo&state=ToolTes#wechat_redirect");
            buttons.add(button);

            button = new Button();
            button.setName("亚马逊");
            button.setType(Button.BUTTON_TYPE_VIEW);
            button.setUrl("https://www.amazon.com");
            buttons.add(button);

            conditionalMenu.setButtons(buttons);

            MatchRule matchRule = new MatchRule();
            matchRule.setTag(2);
            matchRule.setSex(User.SEX_MALE);
            matchRule.setLanguage("zh_CN");
            matchRule.setCountry("中国");
            matchRule.setProvince("浙江");
            matchRule.setCity("杭州");
            matchRule.setPlatform(MatchRule.CLIENT_PLATFORM_TYPE_IOS);
            conditionalMenu.setMatchRule(matchRule);


            menuContext.addConditionalMenu(conditionalMenu);
        } catch (WechatException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteConditionalMenu() {
        try {
            menuContext.deleteConditionalMenu(444464701);
        } catch (WechatException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getMenu() {
        try {
            System.out.println(JSON.toJSONString(menuContext.getMenu()));
        } catch (WechatException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteMenu() {
        try {
            menuContext.deleteMenu();
        } catch (WechatException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getMenuByUser() {
        try {
            System.out.println(JSON.toJSONString(menuContext.getMenu("onkEz1u7V6dw0KvwhAp96UJI3XF0")));
        } catch (WechatException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getMenuInfo() {
        try {
            System.out.println(JSON.toJSONString(menuContext.getMenuInfo()));
        } catch (WechatException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        }
    }
}