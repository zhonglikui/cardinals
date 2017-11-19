package com.zhong.cardinals.net;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhong on 2017/3/10.
 * Retrofit封装的创建工具集
 */

public class NetWorkClient {
    private static String HOST;
    private static OkHttpClient CLIENT;
    private static List<String> HOSTS;

    /**
     * @param host 域名
     */
    public static void init(String host) {
        HOST = host;
    }

    public static void init(String host, OkHttpClient client, List<String> hosts) {
        HOST = host;
        CLIENT = client;
        HOSTS = hosts;
    }

    /**
     *
     * @param serviceClass  API接口类
     * @param <T>  接口类类型
     * @return 创建的api接口
     */
    public static <T> T createService(Class<T> serviceClass) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HOST)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }

    /**
     * 可以传入域名的方法，适用于应用中有多个域名的请求
     *
     * @param host         域名
     * @param serviceClass 接口类型
     * @param <T>          接口
     * @return 创造成功的接口
     */
    public static <T> T createService(String host, Class<T> serviceClass) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(host)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(serviceClass);

    }

    private static OkHttpClient getOkHttpClient() {


        return CLIENT != null ? CLIENT : new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

    }

    public static OkHttpClient getOkhttpClient() {
        X509TrustManager xtm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                X509Certificate[] x509Certificates = new X509Certificate[0];
                return x509Certificates;
            }
        };


        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");

            sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                // .addInterceptor(interceptor)
                .sslSocketFactory(sslContext.getSocketFactory())
                //.protocols(Collections.singletonList(Protocol.HTTP_2))
                .hostnameVerifier(DO_NOT_VERIFY)
                .build();
        return okHttpClient;
    }

    public class DynamicBaseUrlInterceptor implements Interceptor {
        private void setHost(String host) {
            HOST = host;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            HttpUrl newUrl = originalRequest.url().newBuilder().host(HOST).build();
            originalRequest = originalRequest.newBuilder().url(newUrl).build();
            return chain.proceed(originalRequest);
        }
    }

}
