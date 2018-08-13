package xx.wechat.tools.bean.media;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 图文素材
 */
public class Article {
    /**
     * 标题
     */
    @JSONField(name = "title")
    private String title;
    /**
     * 图文消息的封面图片素材id（必须是永久mediaID）
     */
    @JSONField(name = "thumb_media_id")
    private String thumbMediaId;
    /**
     * 作者
     */
    @JSONField(name = "author")
    private String author;
    /**
     * 图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空。如果本字段为没有填写，则默认抓取正文前64个字。
     */
    @JSONField(name = "digest")
    private String digest;
    /**
     * 是否显示封面，0为false，即不显示，1为true，即显示
     */
    @JSONField(name = "show_cover_pic")
    private int showCoverPic;
    /**
     * 图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS,涉及图片url必须来源 "上传图文消息内的图片获取URL"接口获取。外部图片url将被过滤。
     * <p>
     * 如果需要在群发图文中插入小程序，则在调用上传图文消息素材接口时，需在content字段中添加小程序跳转链接，有以下三种样式的可供选择。
     * <p>
     * 小程序卡片跳转小程序，代码示例：
     * <mp-miniprogram data-miniprogram-appid="wx123123123" data-miniprogram-path="pages/index/index" data-miniprogram-title="小程序示例" data-progarm-imageurl="http://mmbizqbic.cn/demo.jpg"></mp-miniprogram>
     * 文字跳转小程序，代码示例：
     * <p><a data-miniprogram-appid="wx123123123" data-miniprogram-path="pages/index" href="">点击文字跳转小程序</a></p>
     * 图片跳转小程序，代码示例：
     * <p><a data-miniprogram-appid="wx123123123" data-miniprogram-path="pages/index" href=""><img src="http://mmbiz.qpic.cn/mmbiz_jpg/demo/0?wx_fmt=jpg" alt="" data-width="null" data-ratio="NaN"></a></p>
     * 参数说明
     * 参数	是否必须	说明
     * data-miniprogram-appid	是	小程序的AppID
     * data-miniprogram-path	是	小程序要打开的路径
     * data-miniprogram-title	是	小程序卡片的标题，不超过35个字
     * data-miniprogram-imageurl	是	小程序卡片的封面图链接，图片必须为1080*864像素
     */
    @JSONField(name = "content")
    private String content;
    /**
     * 图文消息的原文地址，即点击“阅读原文”后的URL
     */
    @JSONField(name = "content_source_url")
    private String contentSourceUrl;
    /**
     * 是否打开评论，0不打开，1打开
     */
    @JSONField(name = "need_open_comment")
    private int needOpenComment;
    /**
     * 是否粉丝才可评论，0所有人可评论，1粉丝才可评论
     */
    @JSONField(name = "only_fans_can_comment")
    private int onlyFansCanComment;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public int getShowCoverPic() {
        return showCoverPic;
    }

    public void setShowCoverPic(int showCoverPic) {
        this.showCoverPic = showCoverPic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentSourceUrl() {
        return contentSourceUrl;
    }

    public void setContentSourceUrl(String contentSourceUrl) {
        this.contentSourceUrl = contentSourceUrl;
    }

    public int getNeedOpenComment() {
        return needOpenComment;
    }

    public void setNeedOpenComment(int needOpenComment) {
        this.needOpenComment = needOpenComment;
    }

    public int getOnlyFansCanComment() {
        return onlyFansCanComment;
    }

    public void setOnlyFansCanComment(int onlyFansCanComment) {
        this.onlyFansCanComment = onlyFansCanComment;
    }
}
