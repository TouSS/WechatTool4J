package xx.wechat.tools.context;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import xx.wechat.tools.bean.AccessToken;
import xx.wechat.tools.bean.CustomerServiceAccount;
import xx.wechat.tools.bean.TemplateMessageItem;
import xx.wechat.tools.bean.message.Article;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;
import xx.wechat.tools.utils.Http;
import xx.wechat.tools.utils.Https;
import xx.wechat.tools.utils.WechatServer;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客服账户/客服消息发送
 */
public class CustomerServiceContext extends PartContext {
    public CustomerServiceContext(AccessToken token) {
        super(token);
    }

    /**
     * 添加客服账户
     *
     * @param customerServiceAccount 客服账户
     * @throws WechatException
     * @throws HttpException
     */
    public void addCustomerServiceAccount(CustomerServiceAccount customerServiceAccount) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/customservice/kfaccount/add?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(customerServiceAccount));
    }

    /**
     * 修改客服账户
     *
     * @param customerServiceAccount 客服账户
     * @throws WechatException
     * @throws HttpException
     */
    public void updateCustomerServiceAccount(CustomerServiceAccount customerServiceAccount) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/customservice/kfaccount/update?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(customerServiceAccount));
    }

    /**
     * 移除客服账户
     *
     * @param customerServiceAccount 客服账户
     * @throws WechatException
     * @throws HttpException
     */
    public void removeCustomerServiceAccount(CustomerServiceAccount customerServiceAccount) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/customservice/kfaccount/del?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(customerServiceAccount));
    }

    /**
     * 设置客服帐号的头像
     *
     * @param account 客服账户名
     * @param headImg 头像图片 - 头像图片文件必须是jpg格式，推荐使用640*640大小的图片以达到最佳效果
     * @throws WechatException
     * @throws HttpException
     */
    public void setCustomerServiceAccountHeadImg(String account, File headImg) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/customservice/kfaccount/uploadheadimg?access_token=" + this.token.getToken() + "&kf_account=" + account;
        Map<String, Object> formData = new HashMap<>();
        formData.put("media", headImg);
        Https.submit(url, formData);
    }

    /**
     * 设置客服帐号的头像
     *
     * @param account 客服账户名
     * @param headImg 头像图片 - 头像图片文件必须是jpg格式，推荐使用640*640大小的图片以达到最佳效果
     * @throws WechatException
     * @throws HttpException
     */
    public void setCustomerServiceAccountHeadImg(String account, String headImg) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/customservice/kfaccount/uploadheadimg?access_token=" + this.token.getToken() + "&kf_account=" + account;
        Map<String, Object> formData = new HashMap<>();
        formData.put("media", new File(headImg));
        Https.submit(url, formData);
    }

    /**
     * 获取所有客服账号
     *
     * @return 客服账号LIST
     * @throws WechatException
     * @throws HttpException
     */
    public List<CustomerServiceAccount> getAllCustomerServiceAccount() throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/customservice/getkflist?access_token=" + this.token.getToken();
        return JSON.parseArray(Https.get(url), CustomerServiceAccount.class);
    }

    /**
     * 发送文本消息
     * <p>
     * 发送文本消息时，支持插入跳小程序的文字链：文本内容<a href="http://www.qq.com" data-miniprogram-appid="appid" data-miniprogram-path="pages/index/index">点击跳小程序</a>
     * 说明：
     * 1.data-miniprogram-appid 项，填写小程序appid，则表示该链接跳小程序；
     * 2.data-miniprogram-path项，填写小程序路径，路径与app.json中保持一致，可带参数；
     * 3.对于不支持data-miniprogram-appid 项的客户端版本，如果有href项，则仍然保持跳href中的网页链接；
     * 4.data-miniprogram-appid对应的小程序必须与公众号有绑定关系。
     *
     * @param receiver               接收者
     * @param content                消息内容
     * @param customerServiceAccount 以某个客服帐号来发消息, 如果为空则以公众号来发
     * @throws WechatException
     * @throws HttpException
     */
    public void sendTextMessage(String receiver, String content, String customerServiceAccount) throws WechatException, HttpException {
        Map<String, Object> messageBox = new HashMap<>();
        messageBox.put("touser", receiver);
        messageBox.put("msgtype", "text");
        Map<String, String> text = new HashMap<>();
        text.put("content", content);
        messageBox.put("text", text);
        if (StringUtils.isNotEmpty(customerServiceAccount)) {
            messageBox.put("customservice", new HashMap<String, String>() {{
                put("kf_account", customerServiceAccount);
            }});
        }
        String url = "https://" + WechatServer.get() + "/cgi-bin/message/custom/send?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(messageBox));
    }

    /**
     * 发送图片消息
     *
     * @param receiver               接收者
     * @param mediaId                图片ID
     * @param customerServiceAccount 以某个客服帐号来发消息, 如果为空则以公众号来发
     * @throws WechatException
     * @throws HttpException
     */
    public void sendImageMessage(String receiver, String mediaId, String customerServiceAccount) throws WechatException, HttpException {
        Map<String, Object> messageBox = new HashMap<>();
        messageBox.put("touser", receiver);
        messageBox.put("msgtype", "image");
        Map<String, String> image = new HashMap<>();
        image.put("media_id", mediaId);
        messageBox.put("image", image);
        if (StringUtils.isNotEmpty(customerServiceAccount)) {
            messageBox.put("customservice", new HashMap<String, String>() {{
                put("kf_account", customerServiceAccount);
            }});
        }
        String url = "https://" + WechatServer.get() + "/cgi-bin/message/custom/send?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(messageBox));
    }

    /**
     * 发送语音消息
     *
     * @param receiver               接收者
     * @param mediaId                语音ID
     * @param customerServiceAccount 以某个客服帐号来发消息, 如果为空则以公众号来发
     * @throws WechatException
     * @throws HttpException
     */
    public void sendVoiceMessage(String receiver, String mediaId, String customerServiceAccount) throws WechatException, HttpException {
        Map<String, Object> messageBox = new HashMap<>();
        messageBox.put("touser", receiver);
        messageBox.put("msgtype", "voice");
        Map<String, String> voice = new HashMap<>();
        voice.put("media_id", mediaId);
        messageBox.put("voice", voice);
        if (StringUtils.isNotEmpty(customerServiceAccount)) {
            messageBox.put("customservice", new HashMap<String, String>() {{
                put("kf_account", customerServiceAccount);
            }});
        }
        String url = "https://" + WechatServer.get() + "/cgi-bin/message/custom/send?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(messageBox));
    }

    /**
     * 发送视频消息
     *
     * @param receiver               接收者
     * @param mediaId                视频ID
     * @param thumbMediaId           视频缩略图ID
     * @param title                  标题
     * @param description            描述
     * @param customerServiceAccount 以某个客服帐号来发消息, 如果为空则以公众号来发
     * @throws WechatException
     * @throws HttpException
     */
    public void sendVideoMessage(String receiver, String mediaId, String thumbMediaId, String title, String description, String customerServiceAccount) throws WechatException, HttpException {
        Map<String, Object> messageBox = new HashMap<>();
        messageBox.put("touser", receiver);
        messageBox.put("msgtype", "video");
        Map<String, String> video = new HashMap<>();
        video.put("media_id", mediaId);
        video.put("thumb_media_id", thumbMediaId);
        video.put("title", title);
        video.put("description", description);
        messageBox.put("video", video);
        if (StringUtils.isNotEmpty(customerServiceAccount)) {
            messageBox.put("customservice", new HashMap<String, String>() {{
                put("kf_account", customerServiceAccount);
            }});
        }
        String url = "https://" + WechatServer.get() + "/cgi-bin/message/custom/send?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(messageBox));
    }

    /**
     * 发送音乐消息
     *
     * @param receiver               接收者
     * @param title                  标题
     * @param description            描述
     * @param musicUrl               音乐链接
     * @param hqMusicUrl             高品质音乐链接
     * @param thumbMediaId           缩略图ID
     * @param customerServiceAccount 以某个客服帐号来发消息, 如果为空则以公众号来发
     * @throws WechatException
     * @throws HttpException
     */
    public void sendMusicMessage(String receiver, String title, String description, String musicUrl, String hqMusicUrl, String thumbMediaId, String customerServiceAccount) throws WechatException, HttpException {
        Map<String, Object> messageBox = new HashMap<>();
        messageBox.put("touser", receiver);
        messageBox.put("msgtype", "music");
        Map<String, String> music = new HashMap<>();
        music.put("musicurl", musicUrl);
        music.put("hqmusicurl", hqMusicUrl);
        music.put("thumb_media_id", thumbMediaId);
        music.put("title", title);
        music.put("description", description);
        messageBox.put("music", music);
        if (StringUtils.isNotEmpty(customerServiceAccount)) {
            messageBox.put("customservice", new HashMap<String, String>() {{
                put("kf_account", customerServiceAccount);
            }});
        }
        String url = "https://" + WechatServer.get() + "/cgi-bin/message/custom/send?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(messageBox));
    }

    /**
     * 发送图文消息（点击跳转到外链） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。
     *
     * @param receiver               接收者
     * @param articles               图文消息集
     * @param customerServiceAccount 以某个客服帐号来发消息, 如果为空则以公众号来发
     * @throws WechatException
     * @throws HttpException
     */
    public void sendNewsMessage(String receiver, List<Article> articles, String customerServiceAccount) throws WechatException, HttpException {
        if (articles.size() > 8) {
            throw new WechatException(-2, "图文消息数目太多, 图文消息条数限制为8条");
        }
        Map<String, Object> messageBox = new HashMap<>();
        messageBox.put("touser", receiver);
        messageBox.put("msgtype", "news");
        Map<String, List<Map<String, String>>> news = new HashMap<>();
        List<Map<String, String>> articleList = new ArrayList<>();
        news.put("articles", articleList);
        articles.stream().forEach(article -> {
            articleList.add(new HashMap<String, String>() {{
                put("title", article.getTitle());
                put("description", article.getDescription());
                put("url", article.getUrl());
                put("picurl", article.getPicUrl());
            }});
        });
        messageBox.put("news", news);
        if (StringUtils.isNotEmpty(customerServiceAccount)) {
            messageBox.put("customservice", new HashMap<String, String>() {{
                put("kf_account", customerServiceAccount);
            }});
        }
        String url = "https://" + WechatServer.get() + "/cgi-bin/message/custom/send?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(messageBox));
    }

    /**
     * 发送图文消息（点击跳转到图文消息页面） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。
     *
     * @param receiver               接收者
     * @param mediaId                图文消息ID
     * @param customerServiceAccount 以某个客服帐号来发消息, 如果为空则以公众号来发
     * @throws WechatException
     * @throws HttpException
     */
    public void sendNewsMessage(String receiver, String mediaId, String customerServiceAccount) throws WechatException, HttpException {
        Map<String, Object> messageBox = new HashMap<>();
        messageBox.put("touser", receiver);
        messageBox.put("msgtype", "mpnews");
        Map<String, String> mpnews = new HashMap<>();
        mpnews.put("media_id", mediaId);
        messageBox.put("mpnews", mpnews);
        if (StringUtils.isNotEmpty(customerServiceAccount)) {
            messageBox.put("customservice", new HashMap<String, String>() {{
                put("kf_account", customerServiceAccount);
            }});
        }
        String url = "https://" + WechatServer.get() + "/cgi-bin/message/custom/send?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(messageBox));
    }

    /**
     * 发送卡券
     *
     * @param receiver               接收者
     * @param cardId                 卡劵ID
     * @param customerServiceAccount 以某个客服帐号来发消息, 如果为空则以公众号来发
     * @throws WechatException
     * @throws HttpException
     */
    public void sendCardMessage(String receiver, String cardId, String customerServiceAccount) throws WechatException, HttpException {
        Map<String, Object> messageBox = new HashMap<>();
        messageBox.put("touser", receiver);
        messageBox.put("msgtype", "wxcard");
        Map<String, String> wxcard = new HashMap<>();
        wxcard.put("card_id", cardId);
        messageBox.put("wxcard", wxcard);
        if (StringUtils.isNotEmpty(customerServiceAccount)) {
            messageBox.put("customservice", new HashMap<String, String>() {{
                put("kf_account", customerServiceAccount);
            }});
        }
        String url = "https://" + WechatServer.get() + "/cgi-bin/message/custom/send?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(messageBox));
    }

    /**
     * 发送小程序卡片（要求小程序与公众号已关联）
     *
     * @param receiver               接收者
     * @param title                  标题
     * @param appid                  APPID
     * @param pagePath               小程序页面路径
     * @param thumbMediaId           缩略图
     * @param customerServiceAccount 以某个客服帐号来发消息, 如果为空则以公众号来发
     * @throws WechatException
     * @throws HttpException
     */
    public void sendMiniProgramPageMessage(String receiver, String title, String appid, String pagePath, String thumbMediaId, String customerServiceAccount) throws WechatException, HttpException {
        Map<String, Object> messageBox = new HashMap<>();
        messageBox.put("touser", receiver);
        messageBox.put("msgtype", "miniprogrampage");
        Map<String, String> miniprogrampage = new HashMap<>();
        miniprogrampage.put("title", title);
        miniprogrampage.put("appid", appid);
        miniprogrampage.put("pagepath", pagePath);
        miniprogrampage.put("thumb_media_id", thumbMediaId);
        messageBox.put("miniprogrampage", miniprogrampage);
        if (StringUtils.isNotEmpty(customerServiceAccount)) {
            messageBox.put("customservice", new HashMap<String, String>() {{
                put("kf_account", customerServiceAccount);
            }});
        }
        String url = "https://" + WechatServer.get() + "/cgi-bin/message/custom/send?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(messageBox));
    }

    /**
     * 向用户下发客服输入状态
     * <p>
     * 说明：
     * 如果不满足发送客服消息的触发条件，则无法下发输入状态。
     * 下发输入状态，需要客服之前30秒内跟用户有过消息交互。
     * 在输入状态中（持续15s），不可重复下发输入态。
     * 在输入状态中，如果向用户下发消息，会同时取消输入状态。
     *
     * @param receiver 接收者
     * @param command  输入状态（"Typing"：对用户下发“正在输入"状态 "CancelTyping"：取消对用户的”正在输入"状态）
     * @throws WechatException
     * @throws HttpException
     */
    public void sendTypingStateMessage(String receiver, String command) throws WechatException, HttpException {
        Map<String, Object> messageBox = new HashMap<>();
        messageBox.put("touser", receiver);
        messageBox.put("command", command);
        String url = "https://" + WechatServer.get() + "/cgi-bin/message/custom/typing?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(messageBox));
    }

    /**
     * 发送模板消息
     * <p>
     * url和miniprogram都是非必填字段，若都不传则模板无跳转；若都传，会优先跳转至小程序。开发者可根据实际需要选择其中一种跳转方式即可。当用户的微信客户端版本不支持跳小程序时，将会跳转至url
     *
     * @param receiver   接收者openid
     * @param templateId 模板ID
     * @param messageUrl 模板跳转链接
     * @param data       模板数据
     * @param appid      跳小程序所需数据, 所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系，暂不支持小游戏）
     * @param pagepath   跳小程序所需数据, 所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar），暂不支持小游戏
     * @return 消息ID
     * @throws WechatException
     * @throws HttpException
     */
    public Long sendTemplateMessage(String receiver, String templateId, String messageUrl, Map<String, TemplateMessageItem> data, String appid, String pagepath) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/message/template/send?access_token=" + this.token.getToken();
        Map<String, Object> messageBox = new HashMap<>();
        messageBox.put("touser", receiver);
        messageBox.put("template_id", templateId);
        messageBox.put("url", messageUrl);
        messageBox.put("data", data);
        if (StringUtils.isNotEmpty(appid) && StringUtils.isNotEmpty(pagepath)) {
            messageBox.put("miniprogram", new HashMap<String, String>() {{
                put("appid", appid);
                put("pagepath", pagepath);
            }});
        }
        return JSON.parseObject(Https.post(url, Http.JSON, JSON.toJSONString(messageBox))).getLong("msgid");
    }
}
