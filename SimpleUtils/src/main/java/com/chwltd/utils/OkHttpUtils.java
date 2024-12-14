package com.chwltd.utils;

import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtils {

    private static final String TAG = "OkHttpUtils";
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static final OkHttpClient client;

    static {
        client = new OkHttpClient();
    }

    // GET 请求
    public static String get(String url){
        String result;
        CompletableFuture<String> future = new CompletableFuture<>();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "GET request failed: " + e.getMessage());
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(Call call, Response response)  {
                if (response.isSuccessful()) {
                    try {
                        if (response.body() != null) {
                            future.complete(response.body().string());
                        }
                    } catch (IOException e) {
                        future.completeExceptionally(e);
                    }
                } else {
                    Log.e(TAG, "GET request not successful. Status code: " + response.code());
                    future.completeExceptionally(new IOException("GET request not successful. Status code: " + response.code()));
                }
            }
        });
        try {
            result = future.get();
        } catch (InterruptedException | ExecutionException e) {
            result = "Error in GET request: " + e.getMessage();
        }
        return result;
    }

    // 修改后的 POST 请求（表单数据，传入用 '&' 分割的字符串）
    public static String postForm(String url, String formData) {
        CompletableFuture<String> future = new CompletableFuture<>();
        FormBody.Builder formBuilder = new FormBody.Builder();
        String[] keyValuePairs = formData.split("&");
        for (String pair : keyValuePairs) {
            String[] parts = pair.split("=");
            if (parts.length == 2) {
                formBuilder.add(parts[0], parts[1]);
            }
        }
        RequestBody requestBody = formBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "POST request failed: " + e.getMessage());
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(Call call, Response response){
                if (response.isSuccessful()) {
                    try {
                        future.complete(response.body().string());
                    } catch (IOException e) {
                        future.completeExceptionally(e);
                    }
                } else {
                    Log.e(TAG, "POST request not successful. Status code: " + response.code());
                    future.completeExceptionally(new IOException("POST request not successful. Status code: " + response.code()));
                }
            }
        });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            return "Error in POST request: " + e.getMessage();
        }
    }

    // JSON POST 请求
    public static String postJson(String url, String jsonData) {
        CompletableFuture<String> future = new CompletableFuture<>();
        RequestBody requestBody = RequestBody.create(JSON, jsonData);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "POST request failed: " + e.getMessage());
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        future.complete(response.body().string());
                    } catch (IOException e) {
                        future.completeExceptionally(e);
                    }
                } else {
                    Log.e(TAG, "POST request not successful. Status code: " + response.code());
                    future.completeExceptionally(new IOException("POST request not successful. Status code: " + response.code()));
                }
            }
        });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            return "Error in POST JSON request: " + e.getMessage();
        }
    }

    // 携带 Cookie 的 GET 请求
    public static String getWithCookie(String url, String cookie) {
        CompletableFuture<String> future = new CompletableFuture<>();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Cookie", cookie)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "GET with cookie request failed: " + e.getMessage());
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        future.complete(response.body().string());
                    } catch (IOException e) {
                        future.completeExceptionally(e);
                    }
                } else {
                    Log.e(TAG, "GET with cookie request not successful. Status code: " + response.code());
                    future.completeExceptionally(new IOException("GET with cookie request not successful. Status code: " + response.code()));
                }
            }
        });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            return "Error in GET with cookie request: " + e.getMessage();
        }
    }

    // 携带 Cookie 的 POST 请求（表单数据）
    public static String postFormWithCookie(String url, String formData, String cookie) {
        CompletableFuture<String> future = new CompletableFuture<>();
        FormBody.Builder formBuilder = new FormBody.Builder();
        String[] keyValuePairs = formData.split("&");
        for (String pair : keyValuePairs) {
            String[] parts = pair.split("=");
            if (parts.length == 2) {
                formBuilder.add(parts[0], parts[1]);
            }
        }
        RequestBody requestBody = formBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Cookie", cookie)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "POST with cookie request failed: " + e.getMessage());
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        future.complete(response.body().string());
                    } catch (IOException e) {
                        future.completeExceptionally(e);
                    }
                } else {
                    Log.e(TAG, "POST with cookie request not successful. Status code: " + response.code());
                    future.completeExceptionally(new IOException("POST with cookie request not successful. Status code: " + response.code()));
                }
            }
        });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            return "Error in POST with cookie request: " + e.getMessage();
        }
    }

    // 携带 Cookie 的 POST 请求（JSON 数据）
    public static String postJsonWithCookie(String url, String jsonData, String cookie) {
        CompletableFuture<String> future = new CompletableFuture<>();
        RequestBody requestBody = RequestBody.create(JSON, jsonData);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Cookie", cookie)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "POST with cookie request failed: " + e.getMessage());
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        future.complete(response.body().string());
                    } catch (IOException e) {
                        future.completeExceptionally(e);
                    }
                } else {
                    Log.e(TAG, "POST with cookie request not successful. Status code: " + response.code());
                    future.completeExceptionally(new IOException("POST with cookie request not successful. Status code: " + response.code()));
                }
            }
        });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            return "Error in POST with cookie request: " + e.getMessage();
        }
    }

    // 携带自定义 Header 的 GET 请求
    public static String getWithHeaders(String url, Map<String, String> headers) {
        CompletableFuture<String> future = new CompletableFuture<>();
        Request.Builder requestBuilder = new Request.Builder().url(url);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            requestBuilder.addHeader(entry.getKey(), entry.getValue());
        }
        Request request = requestBuilder.get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "GET with headers request failed: " + e.getMessage());
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        future.complete(response.body().string());
                    } catch (IOException e) {
                        future.completeExceptionally(e);
                    }
                } else {
                    Log.e(TAG, "GET with headers request not successful. Status code: " + response.code());
                    future.completeExceptionally(new IOException("GET with headers request not successful. Status code: " + response.code()));
                }
            }
        });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            return "Error in GET with headers request: " + e.getMessage();
        }
    }

    // 携带自定义 Header 的 POST 请求（表单数据）
    public static String postFormWithHeaders(String url, String formData, Map<String, String> headers) {
        CompletableFuture<String> future = new CompletableFuture<>();
        FormBody.Builder formBuilder = new FormBody.Builder();
        String[] keyValuePairs = formData.split("&");
        for (String pair : keyValuePairs) {
            String[] parts = pair.split("=");
            if (parts.length == 2) {
                formBuilder.add(parts[0], parts[1]);
            }
        }
        RequestBody requestBody = formBuilder.build();
        Request.Builder requestBuilder = new Request.Builder().url(url);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            requestBuilder.addHeader(entry.getKey(), entry.getValue());
        }
        Request request = requestBuilder.post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "POST with headers request failed: " + e.getMessage());
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        future.complete(response.body().string());
                    } catch (IOException e) {
                        future.completeExceptionally(e);
                    }
                } else {
                    Log.e(TAG, "POST with headers request not successful. Status code: " + response.code());
                    future.completeExceptionally(new IOException("POST with headers request not successful. Status code: " + response.code()));
                }
            }
        });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            return "Error in POST with headers request: " + e.getMessage();
        }
    }

    // 携带自定义 Header 的 POST 请求（JSON 数据）
    public static String postJsonWithHeaders(String url, String jsonData, Map<String, String> headers) {
        CompletableFuture<String> future = new CompletableFuture<>();
        RequestBody requestBody = RequestBody.create(JSON, jsonData);
        Request.Builder requestBuilder = new Request.Builder().url(url);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            requestBuilder.addHeader(entry.getKey(), entry.getValue());
        }
        Request request = requestBuilder.post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "POST with headers request failed: " + e.getMessage());
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        future.complete(response.body().string());
                    } catch (IOException e) {
                        future.completeExceptionally(e);
                    }
                } else {
                    Log.e(TAG, "POST with headers request not successful. Status code: " + response.code());
                    future.completeExceptionally(new IOException("POST with headers request not successful. Status code: " + response.code()));
                }
            }
        });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            return "Error in POST with headers request: " + e.getMessage();
        }
    }

    // 自动设置 Cookie 的请求（假设响应中包含 Set-Cookie 头，将其保存并在后续请求中自动添加）
    public static String requestWithAutoCookie(String url) {
        CompletableFuture<String> future = new CompletableFuture<>();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "Request with auto cookie failed: " + e.getMessage());
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        String responseData = response.body().string();
                        String cookie = response.header("Set-Cookie");
                        if (cookie!= null) {
                            // 保存 Cookie，可以使用 SharedPreferences 等方式
                            // 下次请求时自动添加保存的 Cookie
                        }
                        future.complete(responseData);
                    } catch (IOException e) {
                        future.completeExceptionally(e);
                    }
                } else {
                    Log.e(TAG, "Request with auto cookie not successful. Status code: " + response.code());
                    future.completeExceptionally(new IOException("Request with auto cookie not successful. Status code: " + response.code()));
                }
            }
        });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            return "Error in request with auto cookie: " + e.getMessage();
        }
    }

    // 上传多个文件并带有表单数据的方法
    public static String uploadMultipleFilesWithFormData(String url, List<String> filePaths, String formData) {
        CompletableFuture<String> future = new CompletableFuture<>();
        FormBody.Builder formBuilder = new FormBody.Builder();
        String[] keyValuePairs = formData.split("&");
        for (String pair : keyValuePairs) {
            String[] parts = pair.split("=");
            if (parts.length == 2) {
                formBuilder.add(parts[0], parts[1]);
            }
        }
        for (String filePath : filePaths) {
            File file = new File(filePath);
            if (file.exists()) {
                formBuilder.add("files", file.getName());
            }
        }
        RequestBody requestBody = formBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "File upload with form data failed: " + e.getMessage());
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    try {
                        future.complete(response.body().string());
                    } catch (IOException e) {
                        future.completeExceptionally(e);
                    }
                } else {
                    Log.e(TAG, "File upload with form data not successful. Status code: " + response.code());
                    future.completeExceptionally(new IOException("File upload with form data not successful. Status code: " + response.code()));
                }
            }
        });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            return "Error in file upload with form data: " + e.getMessage();
        }
    }

    // 上传多个文件并带有 JSON 数据的方法，从 filePaths 中解析参数名
    public static String uploadMultipleFilesWithJson(String url, List<String> filePaths, String jsonData) {
        CompletableFuture<String> future = new CompletableFuture<>();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        for (String filePath : filePaths) {
            String[] parts = filePath.split("\n");
            if (parts.length == 2) {
                String paramName = parts[0];
                String actualPath = parts[1];
                File file = new File(actualPath);
                if (file.exists()) {
                    builder.addFormDataPart(paramName, file.getName(), RequestBody.create(MediaType.parse("application/octet-stream"), file));
                }
            }
        }

        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(JSON, jsonData))
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "File upload with json failed: " + e.getMessage());
                future.completeExceptionally(e);
            }

            @Override
            public void onResponse(Call call, Response response) {
                if (response.isSuccessful()) {
                    try {
                        future.complete(response.body().string());
                    } catch (IOException e) {
                        future.completeExceptionally(e);
                    }
                } else {
                    Log.e(TAG, "File upload with json not successful. Status code: " + response.code());
                    future.completeExceptionally(new IOException("File upload with json not successful. Status code: " + response.code()));
                }
            }
        });
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            return "Error in file upload with json: " + e.getMessage();
        }
    }
}