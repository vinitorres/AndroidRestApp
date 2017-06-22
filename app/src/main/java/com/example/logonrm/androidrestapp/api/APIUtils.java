package com.example.logonrm.androidrestapp.api;

/**
 * Created by logonrm on 21/06/2017.
 */

public class APIUtils {

    private static final String URL_BASE_CLIENTE = "http://www.mocky.io/";

    public static AndroidAPI getAndroidApiService() {
        return RetrofitClient.getClient(URL_BASE_CLIENTE).create(AndroidAPI.class);
    }
}
