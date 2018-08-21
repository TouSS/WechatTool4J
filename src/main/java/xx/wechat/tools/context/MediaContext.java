package xx.wechat.tools.context;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.Response;
import xx.wechat.tools.bean.token.AccessToken;
import xx.wechat.tools.bean.media.Article;
import xx.wechat.tools.bean.media.Media;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;
import xx.wechat.tools.utils.Http;
import xx.wechat.tools.utils.Https;
import xx.wechat.tools.utils.ResultCheck;
import xx.wechat.tools.utils.WechatServer;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 素材管理
 */
public class MediaContext extends PartContext {

    public MediaContext(AccessToken token) {
        super(token);
    }

    /**
     * 新增临时素材
     * <p>
     * 说明：
     * <p>
     * 1、临时素材media_id是可复用的。
     * 2、媒体文件在微信后台保存时间为3天，即3天后media_id失效。
     * 3、上传临时素材的格式、大小限制与公众平台官网一致。
     * <p>
     * 图片（image）: 2M，支持PNG\JPEG\JPG\GIF格式
     * 语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式
     * 视频（video）：10MB，支持MP4格式
     * 缩略图（thumb）：64KB，支持JPG格式
     *
     * @param type  媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @param media 素材文件
     * @return 素材ID
     * @throws WechatException
     * @throws HttpException
     */
    public String addTempMedia(String type, File media) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/media/upload?access_token=" + this.token.getToken() + "&type=" + type;
        Map<String, Object> formData = new HashMap<>();
        formData.put("media", media);
        JSONObject jsonObject = JSON.parseObject(Https.submit(url, formData));
        return jsonObject.getString("media_id");
    }


    /**
     * 获取临时素材
     * 视频文件不支持https下载，调用该接口需http协议
     *
     * @param mediaId  素材ID
     * @param savePath 下载保存路径
     * @return 视频消息素材：视频下载URL, 其他：null
     * @throws HttpException
     * @throws WechatException
     */
    public String getTempMedia(String mediaId, String savePath) throws HttpException, WechatException {
        try {
            String url = "https://" + WechatServer.get() + "/cgi-bin/media/get?access_token=" + this.token.getToken() + "&media_id=" + mediaId;
            Response response = Https.request(url);
            String contentType = response.header("Content-Type");
            if (contentType != null && contentType.startsWith("text")) {
                //返回文本数据
                JSONObject jsonObject = JSON.parseObject(response.body().string());
                ResultCheck.checkWechatResult(jsonObject);
                if (jsonObject.containsKey("video_url")) {
                    return jsonObject.getString("video_url");
                } else {
                    throw new WechatException("Unknown response: " + jsonObject.toString());
                }
            } else {
                //非文本数据, 保存至文件
                InputStream inputStream = response.body().byteStream();
                OutputStream outputStream = new FileOutputStream(savePath);
                byte[] buffer = new byte[512];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                inputStream.close();
                outputStream.flush();
                outputStream.close();
                return null;
            }
        } catch (IOException e) {
            throw new WechatException(e);
        }
    }

    /**
     * 高清语音素材获取
     * 公众号可以使用本接口获取从JSSDK的uploadVoice接口上传的临时语音素材，格式为speex，16K采样率。
     * 该音频比上文的临时素材获取接口（格式为amr，8K采样率）更加清晰，适合用作语音识别等对音质要求较高的业务。
     *
     * @param mediaId  素材ID
     * @param savePath 下载保存路径
     * @throws HttpException
     * @throws WechatException
     */
    public void getTempSpeex(String mediaId, String savePath) throws HttpException, WechatException {
        try {
            String url = "https://" + WechatServer.get() + "/cgi-bin/media/get/jssdk?access_token=" + this.token.getToken() + "&media_id=" + mediaId;
            Response response = Https.request(url);
            String contentType = response.header("Content-Type");
            if (contentType != null && contentType.startsWith("text")) {
                //返回文本数据
                JSONObject jsonObject = JSON.parseObject(response.body().string());
                ResultCheck.checkWechatResult(jsonObject);
            } else {
                //非文本数据, 保存至文件
                InputStream inputStream = response.body().byteStream();
                OutputStream outputStream = new FileOutputStream(savePath);
                byte[] buffer = new byte[512];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                inputStream.close();
                outputStream.flush();
                outputStream.close();
            }
        } catch (IOException e) {
            throw new WechatException(e);
        }
    }

    /**
     * 新增永久图文素材
     *
     * @param articleList 图文列表
     * @return 素材ID
     * @throws WechatException
     * @throws HttpException
     */
    public String addPermanentNews(List<Article> articleList) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/material/add_news?access_token=" + this.token.getToken();
        Map<String, List<Article>> news = new HashMap<>();
        news.put("articles", articleList);
        JSONObject jsonObject = JSON.parseObject(Https.post(url, Http.JSON, JSON.toJSONString(news)));
        return jsonObject.getString("media_id");
    }

    /**
     * 上传图文消息内的图片获取URL
     * 上传的图片不占用公众号的素材库中图片数量的5000个的限制。图片仅支持jpg/png格式，大小必须在1MB以下。
     *
     * @param pic 上传图片
     * @return 图片URL
     * @throws WechatException
     * @throws HttpException
     */
    public String uploadNewsPic(File pic) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/media/uploadimg?access_token=" + this.token.getToken();
        Map<String, Object> formData = new HashMap<>();
        formData.put("media", pic);
        return JSON.parseObject(Https.submit(url, formData)).getString("url");
    }

    /**
     * 新增永久素材
     *
     * @param type         媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @param media        素材文件
     * @param title        新增永久视频素材-视频素材的标题
     * @param introduction 新增永久视频素材-视频素材的描述
     * @return 素材ID（新增图片素材时：素材ID:新增的图片素材的图片URL）
     * @throws WechatException
     * @throws HttpException
     */
    public String addPermanentMedia(String type, File media, String title, String introduction) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/material/add_material?access_token=" + this.token.getToken() + "&type=" + type;
        Map<String, Object> formData = new HashMap<>();
        formData.put("media", media);
        if (Media.MEDIA_TYPE_VIDEO.equals(type)) {
            formData.put("formData", JSON.toJSONString(new HashMap<String, String>() {{
                put("title", title);
                put("introduction", introduction);
            }}));
        }
        JSONObject jsonObject = JSON.parseObject(Https.submit(url, formData));
        return jsonObject.getString("media_id") + (jsonObject.containsKey("url") ? ":" + jsonObject.getString("url") : "");
    }

    /**
     * 上传图文消息素材【订阅号与服务号认证后均可用】
     *
     * @param articleList 图文消息，一个图文消息支持1到8条图文
     * @return 素材ID
     * @throws WechatException
     * @throws HttpException
     */
    public String uploadPermanentNews(List<Article> articleList) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/media/uploadnews?access_token=" + this.token.getToken();
        Map<String, List<Article>> news = new HashMap<>();
        news.put("articles", articleList);
        JSONObject jsonObject = JSON.parseObject(Https.post(url, Http.JSON, JSON.toJSONString(news)));
        return jsonObject.getString("media_id");
    }

    /**
     * 获取永久素材
     *
     * @param mediaId  要获取的素材的media_id
     * @param savePath 保存路径
     * @return 视频/图文素材：视频下载URL/图文内容, 其他：null
     * @throws HttpException
     * @throws WechatException
     */
    public String getPermanentMedia(String mediaId, String savePath) throws HttpException, WechatException {
        try {
            String url = "https://" + WechatServer.get() + "/cgi-bin/material/get_material?access_token=" + this.token.getToken();
            Response response = Https.request(url, Http.JSON, JSON.toJSONString(new HashMap<String, String>() {{
                put("media_id", mediaId);
            }}));
            String contentType = response.header("Content-Type");
            if (contentType != null && contentType.startsWith("text")) {
                //返回文本数据
                JSONObject jsonObject = JSON.parseObject(response.body().string());
                ResultCheck.checkWechatResult(jsonObject);
                return jsonObject.toJSONString();
            } else {
                //非文本数据, 保存至文件
                InputStream inputStream = response.body().byteStream();
                OutputStream outputStream = new FileOutputStream(savePath);
                byte[] buffer = new byte[512];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                inputStream.close();
                outputStream.flush();
                outputStream.close();
                return null;
            }
        } catch (IOException e) {
            throw new WechatException(e);
        }
    }

    /**
     * 删除永久素材
     * <p>
     * 注意:
     * 1、请谨慎操作本接口，因为它可以删除公众号在公众平台官网素材管理模块中新建的图文消息、语音、视频等素材（但需要先通过获取素材列表来获知素材的media_id）
     * 2、临时素材无法通过本接口删除
     *
     * @param mediaId 要获取的素材的media_id
     * @throws WechatException
     * @throws HttpException
     */
    public void removePermanentMedia(String mediaId) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/material/del_material?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, String>() {{
            put("media_id", mediaId);
        }}));
    }

    /**
     * 修改永久图文素材
     *
     * @param mediaId 要修改的图文的id
     * @param index   要更新的文章在图文消息中的位置（多图文消息时，此字段才有意义），第一篇为0
     * @param article 图文
     * @throws WechatException
     * @throws HttpException
     */
    public void updatePermanentNews(String mediaId, int index, Article article) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/material/update_news?access_token=" + this.token.getToken();
        Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Object>() {{
            put("media_id", mediaId);
            put("index", index);
            put("articles", article);
        }}));
    }

    /**
     * 获取素材总数
     * 注意：
     * <p>
     * 1.永久素材的总数，也会计算公众平台官网素材管理中的素材
     * 2.图片和图文消息素材（包括单图文和多图文）的总数上限为5000，其他素材的总数上限为1000
     *
     * @return {
     * "voice_count":COUNT,
     * "video_count":COUNT,
     * "image_count":COUNT,
     * "news_count":COUNT
     * }
     * @throws WechatException
     * @throws HttpException
     */
    public Map<String, Integer> getMediaCount() throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/material/get_materialcount?access_token=" + this.token.getToken();
        return JSON.parseObject(Https.get(url), Map.class);
    }

    /**
     * 获取素材列表
     * <p>
     * 注意：
     * 1、获取永久素材的列表，也包含公众号在公众平台官网素材管理模块中新建的图文消息、语音、视频等素材
     * 2、临时素材无法通过本接口获取
     *
     * @param type   素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news）
     * @param offset 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * @param count  返回素材的数量，取值在1到20之间
     * @return 永久图文消息素材列表: {
     * "total_count": TOTAL_COUNT,
     * "item_count": ITEM_COUNT,
     * "item": [{
     * "media_id": MEDIA_ID,
     * "content": {
     * "news_item": [{
     * "title": TITLE,
     * "thumb_media_id": THUMB_MEDIA_ID,
     * "show_cover_pic": SHOW_COVER_PIC(0 / 1),
     * "author": AUTHOR,
     * "digest": DIGEST,
     * "content": CONTENT,
     * "url": URL,
     * "content_source_url": CONTETN_SOURCE_URL
     * },
     * //多图文消息会在此处有多篇文章
     * ]
     * },
     * "update_time": UPDATE_TIME
     * },
     * //可能有多个图文消息item结构
     * ]
     * }
     * 其他类型（图片、语音、视频）: {
     * "total_count": TOTAL_COUNT,
     * "item_count": ITEM_COUNT,
     * "item": [{
     * "media_id": MEDIA_ID,
     * "name": NAME,
     * "update_time": UPDATE_TIME,
     * "url":URL
     * },
     * //可能会有多个素材
     * ]
     * }
     * @throws WechatException
     * @throws HttpException
     */
    public Map<String, Object> getMediaList(String type, int offset, int count) throws WechatException, HttpException {
        String url = "https://" + WechatServer.get() + "/cgi-bin/material/batchget_material?access_token=" + this.token.getToken();
        return JSON.parseObject(Https.post(url, Http.JSON, JSON.toJSONString(new HashMap<String, Object>() {{
            put("type", type);
            put("offset", offset);
            put("count", count);
        }})), Map.class);
    }
}
