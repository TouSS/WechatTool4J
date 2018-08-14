package xx.wechat.tools.context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import xx.wechat.tools.bean.AccessToken;
import xx.wechat.tools.bean.user.Tag;
import xx.wechat.tools.bean.user.User;
import xx.wechat.tools.bean.user.UserBatchInfo;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;
import xx.wechat.tools.utils.Http;
import xx.wechat.tools.utils.Https;
import xx.wechat.tools.utils.WechatServer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 */
public class UserContext extends PartContext {
    public UserContext(AccessToken token) {
        super(token);
    }

    /**
     * 获取用户基本信息（包括UnionID机制）
     *
     * @param openid 普通用户的标识，对当前公众号唯一
     * @param lang   地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
     * @return 用户基本信息
     * @throws WechatException
     * @throws HttpException
     */
    public User getUser(String openid, String lang) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/user/info?access_token=" + this.token.getToken() + "&openid=" + openid + "&lang=" + lang;
        return JSON.parseObject(Https.get(url), User.class);
    }

    /**
     * 批量获取用户基本信息, 最多支持一次拉取100条
     *
     * @param openids 用户的标识
     * @param lang    国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语，默认为zh-CN
     * @return 用户列表
     * @throws WechatException
     * @throws HttpException
     */
    public List<User> getUsers(List<String> openids, String lang) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/user/info/batchget?access_token=" + this.token.getToken();
        List<Map<String, String>> openidList = new ArrayList<>();
        openids.stream().forEach(openid -> {
            openidList.add(new HashMap<String, String>() {{
                put("openid", openid);
                put("lang", lang);
            }});
        });
        JSONObject jsonObject = JSON.parseObject(Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Object>() {{
            put("user_list", openidList);
        }})));
        return jsonObject.getJSONArray("user_info_list").toJavaList(User.class);
    }

    /**
     * 获取用户列表
     * 一次拉取调用最多拉取10000个关注者的OpenID，可以通过多次拉取的方式来满足需求
     *
     * @param openid 第一个拉取的OPENID，不填默认从头开始拉取
     * @return openid列表
     * @throws WechatException
     * @throws HttpException
     */
    public UserBatchInfo getUserList(String openid) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/user/get?access_token=" + this.token.getToken() + "&next_openid=" + openid;
        return JSON.parseObject(Https.get(url), UserBatchInfo.class);
    }

    /**
     * 设置用户备注名
     *
     * @param openid 用户标识
     * @param remark 备注名，长度必须小于30字符
     * @throws WechatException
     * @throws HttpException
     */
    public void setUserRemark(String openid, String remark) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/user/info/updateremark?access_token=" + this.token.getToken() + "&next_openid=" + openid;
        Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, String>() {{
            put("openid", openid);
            put("remark", remark);
        }}));
    }

    /**
     * 创建标签
     * 一个公众号，最多可以创建100个标签。
     *
     * @param tagName 标签名（30个字符以内）
     * @return 标签
     * @throws WechatException
     * @throws HttpException
     */
    public Tag createTag(String tagName) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/tags/create?access_token=" + this.token.getToken();
        return JSON.parseObject(Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Tag>() {{
            put("tag", new Tag(tagName));
        }}))).getObject("tag", Tag.class);
    }

    /**
     * 获取公众号已创建的标签
     *
     * @return 标签列表
     * @throws WechatException
     * @throws HttpException
     */
    public List<Tag> getTags() throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/tags/get?access_token=" + this.token.getToken();
        return JSON.parseObject(Https.get(url)).getJSONArray("tags").toJavaList(Tag.class);
    }

    /**
     * 编辑标签
     *
     * @param tag 标签
     * @throws WechatException
     * @throws HttpException
     */
    public void updateTag(Tag tag) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/tags/update?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Tag>() {{
            put("tag", tag);
        }}));
    }

    /**
     * 删除标签
     *
     * @param tag 标签
     * @throws WechatException
     * @throws HttpException
     */
    public void removeTag(Tag tag) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/tags/delete?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Tag>() {{
            put("tag", tag);
        }}));
    }

    /**
     * 获取标签下粉丝列表
     *
     * @param tagId  标签ID
     * @param openid 第一个拉取的OPENID，不填默认从头开始拉取
     * @return 粉丝列表
     * @throws WechatException
     * @throws HttpException
     */
    public UserBatchInfo getUsersByTag(int tagId, String openid) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/user/tag/get?access_token=" + this.token.getToken();
        return JSON.parseObject(Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Object>() {{
            put("tagid", tagId);
            put("next_openid", openid);
        }})), UserBatchInfo.class);
    }

    /**
     * 批量为用户打标签
     *
     * @param openids 粉丝列表
     * @param tagId   标签ID
     * @throws WechatException
     * @throws HttpException
     */
    public void tagUsers(List<String> openids, int tagId) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/tags/members/batchtagging?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Object>() {{
            put("tagid", tagId);
            put("openid_list", openids);
        }}));
    }

    /**
     * 批量为用户取消标签
     *
     * @param openids 粉丝列表
     * @param tagId   标签ID
     * @throws WechatException
     * @throws HttpException
     */
    public void untagUsers(List<String> openids, int tagId) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/tags/members/batchuntagging?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Object>() {{
            put("tagid", tagId);
            put("openid_list", openids);
        }}));
    }

    /**
     * 获取用户身上的标签列表
     *
     * @param openid 用户标识
     * @return 标签列表
     * @throws WechatException
     * @throws HttpException
     */
    public List<Integer> getUserTags(String openid) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/tags/getidlist?access_token=" + this.token.getToken();
        return JSON.parseObject(Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Object>() {{
            put("openid", openid);
        }}))).getJSONArray("tagid_list").toJavaList(Integer.class);
    }

    /**
     * 获取公众号的黑名单列表
     *
     * @param openid 开始拉取用户, 为空时从开头拉取
     * @return 黑名单列表
     * @throws WechatException
     * @throws HttpException
     */
    public UserBatchInfo getBlackList(String openid) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/tags/members/getblacklist?access_token=" + this.token.getToken();
        return JSON.parseObject(Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Object>() {{
            put("begin_openid", openid);
        }})), UserBatchInfo.class);
    }

    /**
     * 拉黑用户
     *
     * @param openids 需要拉入黑名单的用户的openid，一次拉黑最多允许20个
     * @throws WechatException
     * @throws HttpException
     */
    public void blackUsers(List<String> openids) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/tags/members/batchblacklist?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Object>() {{
            put("openid_list", openids);
        }}));
    }

    /**
     * 取消拉黑用户
     *
     * @param openids 取消黑名单的用户的openid
     * @throws WechatException
     * @throws HttpException
     */
    public void unblackUsers(List<String> openids) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/tags/members/batchunblacklist?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Object>() {{
            put("openid_list", openids);
        }}));
    }
}
