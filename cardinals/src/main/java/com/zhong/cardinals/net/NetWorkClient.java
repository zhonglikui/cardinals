package com.zhong.cardinals.net;

import com.zhong.cardinals.App;

import java.io.IOException;
import java.net.Proxy;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
     * @param serviceClass API接口类
     * @param <T>          接口类类型
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
        if (CLIENT == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS);
            if (App.getInstance().isDebug()) {
                builder.proxy(Proxy.NO_PROXY);
            }
            return builder.build();
        } else {
            return CLIENT;
        }

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
