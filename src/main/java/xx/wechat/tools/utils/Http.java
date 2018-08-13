package xx.wechat.tools.utils;

import com.alibaba.fastjson.JSONException;
import okhttp3.*;
import org.apache.commons.io.FilenameUtils;
import xx.wechat.tools.exception.HttpException;
import xx.wechat.tools.exception.WechatException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Http请求工具类
 */
public class Http {
    /**
     * 文本类型
     **/
    public static final MediaType TEXT = MediaType.parse("text/plain; charset=utf-8");
    /**
     * XML文本类型
     **/
    public static final MediaType XML = MediaType.parse("text/xml; charset=utf-8");
    /**
     * JSON文本类型
     **/
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * Get请求
     *
     * @param url 请求地址
     * @return 返回数据
     * @throws HttpException 请求异常
     */
    public static String get(String url) throws HttpException, WechatException {
        OkHttpClient client = OkHttp.getHttpClient();
        return get(client, url);
    }

    public static String get(OkHttpClient client, String url) throws HttpException, WechatException {
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new HttpException(response.code(), "Unexpected code " + response);
            String result = response.body().string();
            ResultCheck.checkWechatResult(result);
            return result;
        } catch (IOException e) {
            throw new HttpException(e);
        } catch (JSONException e) {
            throw new HttpException(e);
        }
    }

    /**
     * Get请求, 返回数据流 （请在使用完后关闭流）
     *
     * @param url 请求地址
     * @return 数据流
     * @throws HttpException 请求异常
     */
    public static InputStream getStream(String url) throws HttpException {
        OkHttpClient client = OkHttp.getHttpClient();
        return getStream(client, url);
    }

    public static InputStream getStream(OkHttpClient client, String url) throws HttpException {
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new HttpException(response.code(), "Unexpected code " + response);
            return response.body().byteStream();
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    /**
     * Http request请求
     *
     * @param url 请求Url
     * @return 请求返回
     * @throws HttpException
     */
    public static Response request(String url) throws HttpException {
        return request(OkHttp.getHttpClient(), url);
    }

    public static Response request(OkHttpClient client, String url) throws HttpException {
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new HttpException(response.code(), "Unexpected code " + response);
            return response;
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    /**
     * Http request请求
     *
     * @param url 请求Url
     * @param mediaType post数据类型（文本/JSON/XML）
     * @param postData post数据
     * @return
     * @throws HttpException
     */
    public static Response request(String url, String mediaType, String postData) throws HttpException {
        return request(url, MediaType.parse(mediaType), postData);
    }

    public static Response request(String url, MediaType mediaType, String postData) throws HttpException {
        return request(OkHttp.getHttpClient(), url, mediaType, postData);
    }

    public static Response request(OkHttpClient client, String url, MediaType mediaType, String postData) throws HttpException {
        try {
            RequestBody requestBody = RequestBody.create(mediaType, postData);
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new HttpException(response.code(), "Unexpected code " + response);
            return response;
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }

    /**
     * Post请求
     *
     * @param url       请求地址
     * @param mediaType post数据类型（文本/JSON/XML）
     * @param postData  post数据
     * @return 请求返回
     * @throws HttpException http异常
     */
    public static String post(String url, String mediaType, String postData) throws HttpException, WechatException {
        return post(url, MediaType.parse(mediaType), postData);
    }

    public static String post(String url, MediaType mediaType, String postData) throws HttpException, WechatException {
        OkHttpClient client = OkHttp.getHttpClient();
        return post(client, url, mediaType, postData);
    }

    public static String post(OkHttpClient client, String url, MediaType mediaType, String postData) throws HttpException, WechatException {
        try {
            RequestBody requestBody = RequestBody.create(mediaType, postData);

            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new HttpException(response.code(), "Unexpected code " + response);
            String result = response.body().string();
            ResultCheck.checkWechatResult(result);
            return result;
        } catch (IOException e) {
            throw new HttpException(e);
        }
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
        return submit(OkHttp.getHttpClient(), url, formData);
    }

    public static String submit(OkHttpClient client, String url, Map<String, Object> formData) throws HttpException, WechatException {
        try {
            MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
            for (String key : formData.keySet()) {
                Object value = formData.get(key);
                if (value instanceof File) {
                    File file = (File) value;
                    String fileName = file.getName();
                    MediaType mediaType = MediaType.parse(MIME.fromExtension(FilenameUtils.getExtension(fileName)).getType());
                    multipartBodyBuilder.addFormDataPart(key, fileName, RequestBody.create(mediaType, file));
                } else {
                    multipartBodyBuilder.addFormDataPart(key, value.toString());
                }
            }
            RequestBody requestBody = multipartBodyBuilder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new HttpException(response.code(), "Unexpected code " + response);
            String result = response.body().string();
            ResultCheck.checkWechatResult(result);
            return result;
        } catch (IOException e) {
            throw new HttpException(e);
        }
    }
}
