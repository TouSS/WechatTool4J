package xx.wechat.tools.context;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import xx.wechat.tools.bean.AccessToken;
import xx.wechat.tools.bean.message.Message;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;
import xx.wechat.tools.utils.Http;
import xx.wechat.tools.utils.Https;
import xx.wechat.tools.utils.WechatServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 群发处理器
 */
public class BatchSendContext extends PartContext {

    /**
     * 根据用户标签群发
     */
    private final static String SEND_TYPE_TAG = "tag";
    /**
     * 群发给指定openid群
     */
    private final static String SEND_TYPE_OPENID = "openid";
    /**
     * 发送预览至指定openid用户
     */
    private final static String SEND_TYPE_PREVIEW_OPENID = "preview_openid";
    /**
     * 发送预览至指定微信号用户
     */
    private final static String SEND_TYPE_PREVIEW_WXNAME = "preview_wxname";
    /**
     * 发送成功
     */
    private final static String SEND_STATE_SUCCESS = "SEND_SUCCESS";
    /**
     * 发送中
     */
    private final static String SEND_STATE_SENDING = "SENDING";
    /**
     * 发送失败
     */
    private final static String SEND_STATE_FAIL = "SEND_FAIL";
    /**
     * 已删除
     */
    private final static String SEND_STATE_DELETE = "DELETE";

    public BatchSendContext(AccessToken token) {
        super(token);
    }

    /**
     * 通过用户标签群发消息
     *
     * @param tag               用户标签, 如果为空则发送给所有用户
     * @param messageType       消息类型
     * @param mediaId           消息素材ID(使用素材时有效)
     * @param content           文本内容（文本消息时有效）
     * @param title             视频标题（视频消息时有效）
     * @param description       视频描述（视频消息时有效）
     * @param sendIgnoreReprint 当 send_ignore_reprint 参数设置为1时，文章被判定为转载时，且原创文允许转载时，将继续进行群发操作，当 send_ignore_reprint 参数设置为0时，文章被判定为转载时，将停止群发操作，默认为0。
     * @param unique            发送消息唯一标识避免重复推送, 可以为空 （长度限制64字节，如不填，则后台默认以群发范围和群发内容的摘要值做为clientmsgid）
     * @return 群发任务ID
     * @throws WechatException
     * @throws HttpException
     */
    public Long sendMessageByTag(Integer tag, String messageType, String mediaId,
                                 String content, String title, String description,
                                 Integer sendIgnoreReprint, String unique) throws WechatException, HttpException {
        return send(SEND_TYPE_TAG, tag, messageType, mediaId, content, title, description, sendIgnoreReprint, unique);
    }

    /**
     * 通过用户标签群发消息
     *
     * @param openids           接收者OPENID
     * @param messageType       消息类型
     * @param mediaId           消息素材ID(使用素材时有效)
     * @param content           文本内容（文本消息时有效）
     * @param title             视频标题（视频消息时有效）
     * @param description       视频描述（视频消息时有效）
     * @param sendIgnoreReprint 当 send_ignore_reprint 参数设置为1时，文章被判定为转载时，且原创文允许转载时，将继续进行群发操作，当 send_ignore_reprint 参数设置为0时，文章被判定为转载时，将停止群发操作，默认为0。
     * @param unique            发送消息唯一标识避免重复推送, 可以为空 （长度限制64字节，如不填，则后台默认以群发范围和群发内容的摘要值做为clientmsgid）
     * @return 群发任务ID
     * @throws WechatException
     * @throws HttpException
     */
    public Long sendMessageByOpenid(List<String> openids, String messageType, String mediaId,
                                    String content, String title, String description,
                                    Integer sendIgnoreReprint, String unique) throws WechatException, HttpException {
        return send(SEND_TYPE_OPENID, openids, messageType, mediaId, content, title, description, sendIgnoreReprint, unique);
    }

    /**
     * 发送消息预览
     *
     * @param openid            接收者OPENID（openid和微信号必须有一个不为空）
     * @param wxname            接收者微信号（openid和微信号必须有一个不为空）
     * @param messageType       消息类型
     * @param mediaId           消息素材ID(使用素材时有效)
     * @param content           文本内容（文本消息时有效）
     * @param title             视频标题（视频消息时有效）
     * @param description       视频描述（视频消息时有效）
     * @param sendIgnoreReprint 当 send_ignore_reprint 参数设置为1时，文章被判定为转载时，且原创文允许转载时，将继续进行群发操作，当 send_ignore_reprint 参数设置为0时，文章被判定为转载时，将停止群发操作，默认为0。
     * @return 任务ID
     * @throws WechatException
     * @throws HttpException
     */
    public Long sendMessagePreview(String openid, String wxname, String messageType, String mediaId,
                                   String content, String title, String description,
                                   Integer sendIgnoreReprint) throws WechatException, HttpException {
        String sendType, toUser;
        if (StringUtils.isNotEmpty(openid)) {
            sendType = SEND_TYPE_PREVIEW_OPENID;
            toUser = openid;
        } else if (StringUtils.isNotEmpty(wxname)) {
            sendType = SEND_TYPE_PREVIEW_WXNAME;
            toUser = wxname;
        } else {
            throw new WechatException("ToUser should not be empty .");
        }
        return send(sendType, toUser, messageType, mediaId, content, title, description, sendIgnoreReprint, null);
    }

    /**
     * 通过用户标签发送卡劵
     *
     * @param tag    用户标签, 如果为空则发送给所有用户
     * @param cardId 卡劵ID
     * @param unique 发送消息唯一标识避免重复推送, 可以为空 （长度限制64字节，如不填，则后台默认以群发范围和群发内容的摘要值做为clientmsgid）
     * @return 任务ID
     * @throws WechatException
     * @throws HttpException
     */
    public Long sendWxcardByTag(Integer tag, String cardId, String unique) throws WechatException, HttpException {
        return send(SEND_TYPE_TAG, tag, Message.MESSAGE_TYPE_WXCARD, cardId, null, null, null, null, unique);
    }

    /**
     * 通过用户openid发送卡劵
     *
     * @param openids 用户openid
     * @param cardId  卡劵ID
     * @param unique  发送消息唯一标识避免重复推送, 可以为空 （长度限制64字节，如不填，则后台默认以群发范围和群发内容的摘要值做为clientmsgid）
     * @return 任务ID
     * @throws WechatException
     * @throws HttpException
     */
    public Long sendWxcardByOpenid(List<String> openids, String cardId, String unique) throws WechatException, HttpException {
        return send(SEND_TYPE_OPENID, openids, Message.MESSAGE_TYPE_WXCARD, cardId, null, null, null, null, unique);
    }

    /**
     * 发送卡劵预览
     * <p>
     * 注意：
     * <p>
     * 1、只有已经发送成功的消息才能删除
     * 2、删除消息是将消息的图文详情页失效，已经收到的用户，还是能在其本地看到消息卡片。
     * 3、删除群发消息只能删除图文消息和视频消息，其他类型的消息一经发送，无法删除。
     * 4、如果多次群发发送的是一个图文消息，那么删除其中一次群发，就会删除掉这个图文消息也，导致所有群发都失效
     *
     * @param openid 接收者OPENID（openid和微信号必须有一个不为空）
     * @param wxname 接收者微信号（openid和微信号必须有一个不为空）
     * @param cardId 卡劵ID
     * @return 任务ID
     * @throws WechatException
     * @throws HttpException
     */
    public Long sendWxcardByOpenid(String openid, String wxname, String cardId) throws WechatException, HttpException {
        String sendType, toUser;
        if (StringUtils.isNotEmpty(openid)) {
            sendType = SEND_TYPE_PREVIEW_OPENID;
            toUser = openid;
        } else if (StringUtils.isNotEmpty(wxname)) {
            sendType = SEND_TYPE_PREVIEW_WXNAME;
            toUser = wxname;
        } else {
            throw new WechatException("ToUser should not be empty .");
        }
        return send(sendType, toUser, Message.MESSAGE_TYPE_WXCARD, cardId, null, null, null, null, null);
    }

    /**
     * 删除群发【订阅号与服务号认证后均可用】
     *
     * @param msgId 发送出去的消息ID
     * @param index 要删除的文章在图文消息中的位置，第一篇编号为1，该字段不填或填0会删除全部文章
     * @throws WechatException
     * @throws HttpException
     */
    public void removeSend(Long msgId, Integer index) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/message/mass/delete?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Object>() {{
            put("msg_id", msgId);
            put("article_idx", index);
        }}));
    }

    /**
     * 查询群发消息发送状态【订阅号与服务号认证后均可用】
     *
     * @param msgId 群发消息后返回的消息id
     * @return 消息发送后的状态，SEND_SUCCESS表示发送成功，SENDING表示发送中，SEND_FAIL表示发送失败，DELETE表示已删除
     * @throws WechatException
     * @throws HttpException
     */
    public String getSendState(Long msgId) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/message/mass/get?access_token=" + this.token.getToken();
        return JSON.parseObject(Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Object>() {{
            put("msg_id", msgId);
        }}))).getString("msg_status");
    }

    /**
     * 获取群发速度
     * <p>
     * 说明：
     * speed	realspeed
     * 0	    80w/分钟
     * 1	    60w/分钟
     * 2	    45w/分钟
     * 3	    30w/分钟
     * 4	    10w/分钟
     *
     * @return 速度等级
     * @throws WechatException
     * @throws HttpException
     */
    public int getSendSpeed() throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/message/mass/speed/get?access_token=" + this.token.getToken();
        return JSON.parseObject(Https.get(url)).getInteger("speed");
    }

    /**
     * 设置群发速度
     *
     * @param speed 群发速度的级别（在0到4的范围内）
     * @return
     * @throws WechatException
     * @throws HttpException
     */
    public int setSendSpeed(int speed) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/message/mass/speed/set?access_token=" + this.token.getToken();
        return JSON.parseObject(Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Integer>() {{
            put("speed", speed);
        }}))).getInteger("speed");
    }

    /**
     * 群发消息
     *
     * @param sendType          发送类型
     * @param toUser            收信人
     * @param messageType       消息类型
     * @param mediaId           素材消息素材ID
     * @param content           文本消息文本
     * @param title             视频消息标题
     * @param description       视频消息描述
     * @param sendIgnoreReprint 图文消息转载标识
     * @param unique            发送消息唯一标识避免重复推送, 可以为空 （长度限制64字节，如不填，则后台默认以群发范围和群发内容的摘要值做为clientmsgid）
     * @return 群发任务ID
     * @throws WechatException
     * @throws HttpException
     */
    private Long send(String sendType, Object toUser, String messageType, String mediaId, String content,
                      String title, String description, Integer sendIgnoreReprint, String unique) throws WechatException, HttpException {
        Map<String, Object> messageBox = new HashMap<>();
        String url = null;
        switch (sendType) {
            case SEND_TYPE_TAG:
                url = "https://" + WechatServer.get() + "/cgi-bin/message/mass/sendall?access_token=" + this.token.getToken();
                Map<String, Object> receiver = new HashMap<>();
                if (toUser == null) {
                    receiver.put("is_to_all", true);
                } else {
                    receiver.put("is_to_all", true);
                    receiver.put("tag_id", toUser);
                }
                messageBox.put("filter", receiver);
                break;
            case SEND_TYPE_OPENID:
                url = "https://" + WechatServer.get() + "/cgi-bin/message/mass/send?access_token=" + this.token.getToken();
                messageBox.put("touser", toUser);
                break;
            case SEND_TYPE_PREVIEW_OPENID:
                url = "https://" + WechatServer.get() + "/cgi-bin/message/mass/preview?access_token=" + this.token.getToken();
                messageBox.put("touser", toUser);
                break;
            case SEND_TYPE_PREVIEW_WXNAME:
                url = "https://" + WechatServer.get() + "/cgi-bin/message/mass/preview?access_token=" + this.token.getToken();
                messageBox.put("towxname", toUser);
                break;
            default:
                throw new WechatException("Wrong batch send type: " + sendType);
        }

        switch (messageType) {
            case Message.MESSAGE_TYPE_NEWS:
            case Message.MESSAGE_TYPE_MPNEWS:
                messageBox.put("msgtype", Message.MESSAGE_TYPE_MPNEWS);
                messageBox.put("mpnews", new HashMap<String, String>() {{
                    put("media_id", mediaId);
                }});
                messageBox.put("send_ignore_reprint", sendIgnoreReprint);
                break;
            case Message.MESSAGE_TYPE_TEXT:
                messageBox.put("msgtype", Message.MESSAGE_TYPE_TEXT);
                messageBox.put("text", new HashMap<String, String>() {{
                    put("content", content);
                }});
                break;
            case Message.MESSAGE_TYPE_VOICE:
                messageBox.put("msgtype", Message.MESSAGE_TYPE_VOICE);
                messageBox.put("voice", new HashMap<String, String>() {{
                    put("media_id", mediaId);
                }});
                break;
            case Message.MESSAGE_TYPE_IMAGE:
                messageBox.put("msgtype", Message.MESSAGE_TYPE_IMAGE);
                messageBox.put("image", new HashMap<String, String>() {{
                    put("media_id", mediaId);
                }});
                break;
            case Message.MESSAGE_TYPE_WXCARD:
                messageBox.put("msgtype", Message.MESSAGE_TYPE_WXCARD);
                messageBox.put("wxcard", new HashMap<String, String>() {{
                    put("card_id", mediaId);
                }});
                break;
            case Message.MESSAGE_TYPE_VIDEO:
            case Message.MESSAGE_TYPE_MPVIDEO:
                messageBox.put("msgtype", Message.MESSAGE_TYPE_MPVIDEO);
                messageBox.put("mpvideo", new HashMap<String, String>() {{
                    put("media_id", getVideoSendMediaId(mediaId, title, description));
                    put("title", title);
                    put("description", description);
                }});
                break;
            default:
                throw new WechatException("Wrong batch send message type: " + messageType);

        }
        if (StringUtils.isNotEmpty(unique)) {
            messageBox.put("clientmsgid", unique);
        }
        return JSON.parseObject(Https.post(url, Http.JSON, JSON.toJSONString(messageBox))).getLong("msg_id");
    }

    /**
     * 群发视频消息时获取可用视频ID
     *
     * @param mediaId     视频素材ID
     * @param title       标题
     * @param description 描述
     * @return 可用外链ID
     * @throws WechatException
     * @throws HttpException
     */
    private String getVideoSendMediaId(String mediaId, String title, String description) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/media/uploadvideo?access_token=" + this.token.getToken();
        return JSON.parseObject(Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, String>() {{
            put("media_id", mediaId);
            put("title", title);
            put("description", description);
        }}))).getString("media_id");
    }
}
