package xx.wechat.tools.context;

import com.alibaba.fastjson.JSON;
import xx.wechat.tools.bean.token.AccessToken;
import xx.wechat.tools.bean.NewsComment;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;
import xx.wechat.tools.utils.Http;
import xx.wechat.tools.utils.Https;
import xx.wechat.tools.utils.WechatServer;

import java.util.HashMap;

/**
 * 图文消息留言/评论管理
 */
public class NewsCommentContext extends PartContext {
    public NewsCommentContext(AccessToken token) {
        super(token);
    }

    /**
     * 打开已群发文章评论
     *
     * @param msgDataId 群发返回的msg_data_id
     * @param index     多图文时，用来指定第几篇图文，从0开始，不带默认操作该msg_data_id的第一篇图文
     * @throws WechatException
     * @throws HttpException
     */
    public void openComment(Integer msgDataId, Integer index) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/comment/open?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Object>() {{
            put("msg_data_id", msgDataId);
            put("index", index);
        }}));
    }

    /**
     * 关闭已群发文章评论
     *
     * @param msgDataId 群发返回的msg_data_id
     * @param index     多图文时，用来指定第几篇图文，从0开始，不带默认操作该msg_data_id的第一篇图文
     * @throws WechatException
     * @throws HttpException
     */
    public void closeComment(Integer msgDataId, Integer index) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/comment/close?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Object>() {{
            put("msg_data_id", msgDataId);
            put("index", index);
        }}));
    }

    /**
     * 查看指定文章的评论数据
     *
     * @param msgDataId 群发返回的msg_data_id
     * @param index     多图文时，用来指定第几篇图文，从0开始，不带默认返回该msg_data_id的第一篇图文
     * @param begin     起始位置
     * @param count     获取数目（>=50会被拒绝）
     * @param type      type=0 普通评论&精选评论 type=1 普通评论 type=2 精选评论
     * @return 评论数据
     * @throws WechatException
     * @throws HttpException
     */
    public NewsComment getComment(Integer msgDataId, Integer index, Integer begin, Integer count, Integer type) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/comment/list?access_token=" + this.token.getToken();
        return JSON.parseObject(Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Object>() {{
            put("msg_data_id", msgDataId);
            put("index", index);
            put("begin", begin);
            put("count", count);
            put("type", type);
        }})), NewsComment.class);
    }

    /**
     * 将评论标记精选
     *
     * @param msgDataId     群发返回的msg_data_id
     * @param index         多图文时，用来指定第几篇图文，从0开始，不带默认操作该msg_data_id的第一篇图文
     * @param userCommentId 用户评论id
     * @throws WechatException
     * @throws HttpException
     */
    public void markelectComment(Integer msgDataId, Integer index, Integer userCommentId) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/comment/markelect?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Object>() {{
            put("msg_data_id", msgDataId);
            put("index", index);
            put("user_comment_id", userCommentId);
        }}));
    }

    /**
     * 将评论取消精选
     *
     * @param msgDataId     群发返回的msg_data_id
     * @param index         多图文时，用来指定第几篇图文，从0开始，不带默认操作该msg_data_id的第一篇图文
     * @param userCommentId 用户评论id
     * @throws WechatException
     * @throws HttpException
     */
    public void unmarkelectComment(Integer msgDataId, Integer index, Integer userCommentId) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/comment/unmarkelect?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Object>() {{
            put("msg_data_id", msgDataId);
            put("index", index);
            put("user_comment_id", userCommentId);
        }}));
    }

    /**
     * 删除评论
     *
     * @param msgDataId     群发返回的msg_data_id
     * @param index         多图文时，用来指定第几篇图文，从0开始，不带默认操作该msg_data_id的第一篇图文
     * @param userCommentId 用户评论id
     * @throws WechatException
     * @throws HttpException
     */
    public void removeComment(Integer msgDataId, Integer index, Integer userCommentId) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/comment/delete?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Object>() {{
            put("msg_data_id", msgDataId);
            put("index", index);
            put("user_comment_id", userCommentId);
        }}));
    }

    /**
     * 回复评论
     *
     * @param msgDataId     群发返回的msg_data_id
     * @param index         多图文时，用来指定第几篇图文，从0开始，不带默认操作该msg_data_id的第一篇图文
     * @param userCommentId 用户评论id
     * @param content       回复内容
     * @throws WechatException
     * @throws HttpException
     */
    public void replyComment(Integer msgDataId, Integer index, Integer userCommentId, String content) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/comment/reply/add?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Object>() {{
            put("msg_data_id", msgDataId);
            put("index", index);
            put("user_comment_id", userCommentId);
            put("content", content);
        }}));
    }

    /**
     * 删除回复
     *
     * @param msgDataId     群发返回的msg_data_id
     * @param index         多图文时，用来指定第几篇图文，从0开始，不带默认操作该msg_data_id的第一篇图文
     * @param userCommentId 用户评论id
     * @throws WechatException
     * @throws HttpException
     */
    public void removeCommentReply(Integer msgDataId, Integer index, Integer userCommentId) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/comment/reply/delete?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Object>() {{
            put("msg_data_id", msgDataId);
            put("index", index);
            put("user_comment_id", userCommentId);
        }}));
    }
}
