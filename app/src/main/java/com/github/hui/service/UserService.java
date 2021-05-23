package com.github.hui.service;

import com.github.hui.model.ServiceConst;
import com.github.hui.model.ServiceResponse;
import com.github.hui.model.User;
import com.github.hui.util.JsonUtil;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UserService {
    // user login
    public ServiceResponse login(User user) {
        // check user param

        // req am server
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtil.obj2str(user));
        Request request = new Request.Builder()
                .url(ServiceConst.AM_USER_AUTH_URL)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return new ServiceResponse("msg from am server: " + response.body().string());
            }
        } catch (Exception e) {
            return new ServiceResponse("get msg from error, ex is:" + e.getMessage());
        }

        return ServiceResponse.OK;
    }

    // regist
    public ServiceResponse register(User user) {
        // check user param

        // req am server
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JsonUtil.obj2str(user));
        Request request = new Request.Builder()
                .url(ServiceConst.AM_USER_REGISTER_URL)
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return new ServiceResponse("msg from am server: " + response.body().string());
            }
        } catch (Exception e) {
            return new ServiceResponse("get msg from error, ex is:" + e.getMessage());
        }

        return ServiceResponse.OK;
    }
}
