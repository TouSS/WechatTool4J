package xx.wechat.tools.context;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import xx.wechat.tools.bean.AccessToken;
import xx.wechat.tools.bean.menu.Button;
import xx.wechat.tools.bean.menu.ConditionalMenu;
import xx.wechat.tools.bean.menu.MatchRule;
import xx.wechat.tools.bean.menu.Menu;
import xx.wechat.tools.bean.user.User;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MenuContextTest {
    MenuContext menuContext;

    @Before
    public void setUp() throws Exception {
        AccessToken accessToken = JSON.parseObject("{\"access_token\":\"12_jVSNXK1EJwfcBlkA-gfbLoBXW3gsGHro2fY9SkUza2q15GYpirgTpyfH1n8TRa-f0LyzLjhr6obL-1LIFIsyCgtoLNFnlbkbPUhZitrxPbmitjE_a9wiZ78dx6Sh8ykiBk0XpG0Y9oWaOR1WTZMjAIAJPC\",\"apply_timestamp\":1534492930,\"expired\":false,\"expires_in\":7200}", AccessToken.class);
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
            button.setName("淘宝");
            button.setType(Button.BUTTON_TYPE_VIEW);
            button.setUrl("https://www.taobao.com");
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
            button.setName("亚马逊");
            button.setType(Button.BUTTON_TYPE_VIEW);
            button.setUrl("https://www.amazon.com");
            buttons.add(button);

            conditionalMenu.setButtons(buttons);

            MatchRule matchRule = new MatchRule();
            matchRule.setTag(100);
            conditionalMenu.setMatchRule(matchRule);


            menuContext.addConditionalMenu(conditionalMenu);
        } catch (WechatException e) {
            e.printStackTrace();
        } catch (HttpException e) {
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