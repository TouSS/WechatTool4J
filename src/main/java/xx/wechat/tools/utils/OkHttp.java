package xx.wechat.tools.utils;

import okhttp3.OkHttpClient;
import xx.wechat.tools.exception.HttpException;

import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;


/**
 * OkHttp工具类
 */
public class OkHttp {

    /**
     * Http请求
     **/
    private volatile static OkHttpClient httpClient;
    /**
     * Https请求
     **/
    private volatile static OkHttpClient httpsClient;

    /**
     * 获取Http请求客户端
     *
     * @return httpClient
     */
    public static synchronized OkHttpClient getHttpClient() {
        if (httpClient == null) {
            httpClient = new OkHttpClient();
        }
        return httpClient;
    }

    /**
     * 获取Https请求客户端
     *
     * @return httpsClient
     */
    public static synchronized OkHttpClient getHttpsClient() throws HttpException {
        if (httpsClient == null) {
            if (httpsClient == null) {
                try {
                    TrustManager trustManager = new TrustManager();
                    SSLContext sslContext = SSLContext.getInstance("TLS");
                    sslContext.init(null, new TrustManager[]{trustManager}, new SecureRandom());
                    httpsClient = new OkHttpClient.Builder()
                            .sslSocketFactory(sslContext.getSocketFactory(), trustManager)
                            .hostnameVerifier(new Verifier())
                            .build();
                } catch (Exception e) {

                    throw new HttpException(e);
                }
            }
        }
        return httpsClient;
    }


    private static class TrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static class Verifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }

    }
}
