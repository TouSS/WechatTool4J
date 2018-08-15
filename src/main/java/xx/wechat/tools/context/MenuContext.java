package xx.wechat.tools.context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import xx.wechat.tools.bean.AccessToken;
import xx.wechat.tools.bean.menu.Menu;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;
import xx.wechat.tools.bean.menu.ConditionalMenu;
import xx.wechat.tools.utils.Http;
import xx.wechat.tools.utils.Https;
import xx.wechat.tools.utils.WechatServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单操作方法
 */
public class MenuContext extends PartContext {
    public MenuContext(AccessToken token) {
        super(token);
    }

    /**
     * 自定义菜单创建
     *
     * @param menu 菜单
     * @throws WechatException
     * @throws HttpException
     */
    public void createMenu(Menu menu) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/menu/create?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(menu));
    }

    /**
     * 创建个性化菜单
     *
     * @param conditionalMenu 个性化菜单
     * @return 菜单ID
     * @throws WechatException
     * @throws HttpException
     */
    public Integer addConditionalMenu(ConditionalMenu conditionalMenu) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/menu/addconditional?access_token=" + this.token.getToken();
        JSONObject jsonObject = JSON.parseObject(Https.post(url, Http.JSON, JSON.toJSONString(conditionalMenu)));
        return jsonObject.getInteger("menuid");
    }

    /**
     * 删除个性化菜单
     *
     * @param conditionalMenu 个性化菜单-menuId
     * @throws WechatException
     * @throws HttpException
     */
    public void deleteConditionalMenu(ConditionalMenu conditionalMenu) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/menu/delconditional?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(conditionalMenu));
    }

    /**
     * 获取用户匹配菜单
     *
     * @param user 粉丝的OpenID或微信号
     * @return
     * @throws WechatException
     * @throws HttpException
     */
    public Menu getMenu(String user) throws WechatException, HttpException {
        Map<String, String> postData = new HashMap<>();
        postData.put("user_id", user);
        String url = "https://" + WechatServer.get() + "cgi-bin/menu/trymatch?access_token=" + this.token.getToken();
        return JSON.parseObject(Https.post(url, Http.JSON, JSON.toJSONString(postData)), Menu.class);
    }

    /**
     * 删除所有自定义菜单
     *
     * @throws WechatException
     * @throws HttpException
     */
    public void deleteMenu() throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/menu/delete?access_token=" + this.token.getToken();
        Https.get(url);
    }

    /**
     * 获取所有菜单, 在设置了个性化菜单后，使用本自定义菜单查询接口可以获取默认菜单和全部个性化菜单信息
     * @return 所有菜单(数组第一个为默认菜单, 后面为个性化菜单)
     * @throws WechatException
     * @throws HttpException
     */
    public List<Menu> getMenu() throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/menu/get?access_token=" + this.token.getToken();
        JSONObject jsonObject = JSON.parseObject(Https.get(url));
        List<Menu> menus = new ArrayList<>();
        if(jsonObject.containsKey("menu")) {
            menus.add((Menu) jsonObject.get("menu"));
        }
        if(jsonObject.containsKey("conditionalmenu")) {
            menus.addAll(jsonObject.getJSONArray("conditionalmenu").toJavaList(ConditionalMenu.class));
        }
        return menus;
    }

    /**
     * 公众号当前使用的自定义菜单的配置，如果公众号是通过API调用设置的菜单，则返回菜单的开发配置，而如果公众号是在公众平台官网通过网站功能发布菜单，则本接口返回运营者设置的菜单配置
     * @return 菜单信息
     * @throws WechatException
     * @throws HttpException
     */
    public Map<String, Object> getMenuInfo() throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/get_current_selfmenu_info?access_token=" + this.token.getToken();
        return JSON.parseObject(Https.get(url), Map.class);
    }
}
