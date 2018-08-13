package xx.wechat.tools.utils;

import okhttp3.*;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;

import java.io.InputStream;
import java.util.Map;

/**
 * Https工具
 */
public class Https {
    /**
     * Get请求
     *
     * @param url 请求地址
     * @return 返回数据
     * @throws HttpException 请求异常
     */
    public static String get(String url) throws HttpException, WechatException {
        OkHttpClient client = OkHttp.getHttpsClient();
        return Http.get(client, url);
    }

    /**
     * Get请求, 返回数据流 （请在使用完后关闭流）
     *
     * @param url 请求地址
     * @return 数据流
     * @throws HttpException 请求异常
     */
    public static InputStream getStream(String url) throws HttpException {
        OkHttpClient client = OkHttp.getHttpsClient();
        return Http.getStream(client, url);
    }

    /**
     * Https request请求
     *
     * @param url 请求Url
     * @return 请求返回
     * @throws HttpException
     */
    public static Response request(String url) throws HttpException {
        return Http.request(OkHttp.getHttpsClient(), url);
    }

    /**
     * Https request请求
     *
     * @param url       请求Url
     * @param mediaType post数据类型（文本/JSON/XML）
     * @param postData  post数据
     * @return
     * @throws HttpException
     */
    public static Response request(String url, String mediaType, String postData) throws HttpException {
        return request(url, MediaType.parse(mediaType), postData);
    }

    public static Response request(String url, MediaType mediaType, String postData) throws HttpException {
        return Http.request(OkHttp.getHttpsClient(), url, mediaType, postData);
    }

    /**
     * Post请求
     *
     * @param url       请求地址
     * @param mediaType post数据类型（文本/JSON/XML）
     * @param postData  post数据
     * @return
     * @throws HttpException http异常
     */
    public static String post(String url, String mediaType, String postData) throws HttpException, WechatException {
        return post(url, MediaType.parse(mediaType), postData);
    }

    public static String post(String url, MediaType mediaType, String postData) throws HttpException, WechatException {
        OkHttpClient client = OkHttp.getHttpsClient();
        return Http.post(client, url, mediaType, postData);
    }

    /**
     * 表单数据提交
     *
     * @param url      提交地址
     * @param formData 表单数据
     * @return 请求返回
     * @throws HttpException
     * @throws WechatException
     */
    public static String submit(String url, Map<String, Object> formData) throws HttpException, WechatException {
        return Http.submit(OkHttp.getHttpsClient(), url, formData);
    }
}
