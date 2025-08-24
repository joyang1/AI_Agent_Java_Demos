package com.ai.common.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;

/**
 * @author tommy
 * @Time 2025年08月16日 23:15
 * @Sofaware IntelliJ IDEA
 * @Email tingzai.yang@gmail.com
 * @FileName SimpleOkHttpClient.java
 */
public class SimpleOkHttpClient {

    // 1. 创建一个共享的 OkHttpClient 实例
    // 这个实例应该被复用，因为它管理着连接池和线程池
    private static final OkHttpClient client = new OkHttpClient();

    /**
     * 使用 OkHttp 发送 GET 请求
     *
     * @param urlString 要请求的URL
     * @return 响应体字符串，如果请求失败则返回 null
     * @throws IOException 如果发生网络错误
     */
    public static String sendGetRequest(String urlString) throws IOException {
        // 2. 创建一个 Request 对象
        Request request = new Request.Builder()
                .url(urlString)
                .get() // .get() 是默认的，可以省略
                .build();

        // 3. 使用 client 发送请求，并获取 Response
        // 使用 try-with-resources 语句确保 Response 对象被正确关闭
        // Response 的 body 必须被关闭，以释放底层资源
        try (Response response = client.newCall(request).execute()) {

            // 检查响应是否成功 (HTTP 状态码 200-299)
            if (!response.isSuccessful()) {
                System.err.println("请求失败，响应码: " + response.code());
                return null;
            }

            // 4. 获取响应体
            // 注意：response.body().string() 只能被调用一次！
            // 因为它会将整个响应体加载到内存中。
            ResponseBody body = response.body();
            return body != null ? body.string() : null;
        }
    }

    // 主方法，用于测试
    public static void main(String[] args) {
        String testUrl = "https://www.baidu.com";
        try {
            String responseBody = sendGetRequest(testUrl);
            if (responseBody != null) {
                System.out.println("Response body:");
                System.out.println(responseBody);
            }
        } catch (IOException e) {
            System.err.println("请求时发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
