package xx.wechat.tools.bean.user;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * 用户基本信息
 */
public class User {
    /**
     * 地区语言版本-简体
     */
    public final static String LANGUAGE_ZH_CN = "zh_CN";
    /**
     * 地区语言版本-繁体
     */
    public final static String LANGUAGE_ZH_TW = "zh_TW";
    /**
     * 地区语言版本-英语
     */
    public final static String LANGUAGE_EN = "en ";
    /**
     * 男性
     */
    public final static int SEX_MALE = 1;
    /**
     * 女性
     */
    public final static int SEX_FEMALE = 2;
    /**
     * 其他
     */
    public final static int SEX_OTHER = 0;

    /**
     * 公众号搜索
     */
    public final static String ADD_SCENE_SEARCH = "ADD_SCENE_SEARCH";
    /**
     * 公众号迁移
     */
    public final static String ADD_SCENE_ACCOUNT_MIGRATION = "ADD_SCENE_ACCOUNT_MIGRATION";
    /**
     * 名片分享
     */
    public final static String ADD_SCENE_PROFILE_CARD = "ADD_SCENE_PROFILE_CARD";
    /**
     * 扫描二维码
     */
    public final static String ADD_SCENE_QR_CODE = "ADD_SCENE_QR_CODE";
    /**
     * LINK 图文页内名称点击
     */
    public final static String ADD_SCENEPROFILE = "ADD_SCENEPROFILE";
    /**
     * 图文页右上角菜单
     */
    public final static String ADD_SCENE_PROFILE_ITEM = "ADD_SCENE_PROFILE_ITEM";
    /**
     * 支付后关注
     */
    public final static String ADD_SCENE_PAID = "ADD_SCENE_PAID";
    /**
     * 其他
     */
    public final static String ADD_SCENE_OTHERS = "ADD_SCENE_OTHERS";

    /**
     * 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息
     */
    @JSONField(name = "subscribe")
    private int subscribe;
    /**
     * 用户的标识，对当前公众号唯一
     */
    @JSONField(name = "openid")
    private String openid;
    /**
     * 用户的昵称
     */
    @JSONField(name = "nickname")
    private String nickname;
    /**
     * 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
     */
    @JSONField(name = "sex")
    private int sex;
    /**
     * 用户所在城市
     */
    @JSONField(name = "city")
    private String city;
    /**
     * 用户所在国家
     */
    @JSONField(name = "country")
    private String country;
    /**
     * 用户所在省份
     */
    @JSONField(name = "province")
    private String province;
    /**
     * 用户的语言，简体中文为zh_CN
     */
    @JSONField(name = "language")
    private String language;
    /**
     * 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效
     */
    @JSONField(name = "headimgurl")
    private String headImgUrl;
    /**
     * 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
     */
    @JSONField(name = "subscribe_time")
    private long subscribeTime;
    /**
     * 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段
     */
    @JSONField(name = "unionid")
    private String unionId;
    /**
     * 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
     */
    @JSONField(name = "remark")
    private String remark;
    /**
     * 用户所在的分组ID（兼容旧的用户分组接口）
     */
    @JSONField(name = "groupid")
    private int groupId;
    /**
     * 用户被打上的标签ID列表
     */
    @JSONField(name = "tagid_list")
    private List<Integer> tagIdList;
    /**
     * 返回用户关注的渠道来源，
     * ADD_SCENE_SEARCH 公众号搜索，
     * ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，
     * ADD_SCENE_PROFILE_CARD 名片分享，
     * ADD_SCENE_QR_CODE 扫描二维码，
     * ADD_SCENEPROFILE LINK 图文页内名称点击，
     * ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，
     * ADD_SCENE_PAID 支付后关注，
     * ADD_SCENE_OTHERS 其他
     */
    @JSONField(name = "subscribe_scene")
    private String subscribeScene;
    /**
     * 二维码扫码场景（开发者自定义）
     */
    @JSONField(name = "qr_scene")
    private String qrScene;
    /**
     * 二维码扫码场景描述（开发者自定义）
     */
    @JSONField(name = "qr_scene_str")
    private String qrSceneStr;
    /**
     * 用户特权信息, 数组, 如微信沃卡用户为（chinaunicom）
     */
    @JSONField(name = "privilege")
    private List<String> privilege;

    public int getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(int subscribe) {
        this.subscribe = subscribe;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public long getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(long subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public List<Integer> getTagIdList() {
        return tagIdList;
    }

    public void setTagIdList(List<Integer> tagIdList) {
        this.tagIdList = tagIdList;
    }

    public String getSubscribeScene() {
        return subscribeScene;
    }

    public void setSubscribeScene(String subscribeScene) {
        this.subscribeScene = subscribeScene;
    }

    public String getQrScene() {
        return qrScene;
    }

    public void setQrScene(String qrScene) {
        this.qrScene = qrScene;
    }

    public String getQrSceneStr() {
        return qrSceneStr;
    }

    public void setQrSceneStr(String qrSceneStr) {
        this.qrSceneStr = qrSceneStr;
    }

    public List<String> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<String> privilege) {
        this.privilege = privilege;
    }
}
