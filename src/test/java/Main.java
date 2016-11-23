import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.SocketConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by merc on 11/11/16.
 */
public class Main {

    public static final RequestConfig REQUEST_CONFIG = RequestConfig.custom()
            .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
            .setSocketTimeout(60000)
            .setConnectTimeout(5000)
            .setConnectionRequestTimeout(5000)
            .build();//true - no differences in request time
    public static final SocketConfig SOCKET_CONFIG = SocketConfig.custom()
            .setSoKeepAlive(true) //true - no differences in request time
            .setTcpNoDelay(true)
            .build();
    public static final PoolingHttpClientConnectionManager CONN_MANAGER = new PoolingHttpClientConnectionManager();
    public static final String HTTP_WWW_GOOGLE_COM = "https://api.vk.com/method/database.getCountries";

    static {
        CONN_MANAGER.setMaxTotal(50);
        CONN_MANAGER.setDefaultMaxPerRoute(2);
    }

    public static void main(String[] args) throws IOException, InterruptedException {

        while (true){
            new Main().work();
            Thread.sleep(10000);
        }
    }

    private void work() throws IOException {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultSocketConfig(SOCKET_CONFIG)
                .setConnectionManager(CONN_MANAGER)
                .setDefaultRequestConfig(REQUEST_CONFIG)
                //.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36")
                .build();

//1
        long t11 = System.currentTimeMillis();
        try(CloseableHttpResponse response1 = httpClient.execute(new HttpGet(HTTP_WWW_GOOGLE_COM))) {
            int code1 = response1.getStatusLine().getStatusCode();
            System.out.println("Code 1 "+code1);
            EntityUtils.consume(response1.getEntity());
        }
        long t12 = System.currentTimeMillis();
        long reqTime1 = t12 - t11;
        System.out.println("Time taken1 "+reqTime1);
//2
        long t21 = System.currentTimeMillis();
        try(CloseableHttpResponse response2 = httpClient.execute(new HttpGet(HTTP_WWW_GOOGLE_COM))) {
            int code2 = response2.getStatusLine().getStatusCode();
            System.out.println("Code 2 "+code2);
            EntityUtils.consume(response2.getEntity());
        }
        long t22 = System.currentTimeMillis();
        long reqTime2 = t22 - t21;
        System.out.println("Time taken2 "+reqTime2);
        //3
        long t31 = System.currentTimeMillis();
        try(CloseableHttpResponse response3 = httpClient.execute(new HttpGet(HTTP_WWW_GOOGLE_COM))) {
            int code3 = response3.getStatusLine().getStatusCode();
            System.out.println("Code 3 "+code3);
            EntityUtils.consume(response3.getEntity());
        }
        long t32 = System.currentTimeMillis();
        long reqTime3 = t32 - t31;
        System.out.println("Time taken3 "+reqTime3);

    }
}
