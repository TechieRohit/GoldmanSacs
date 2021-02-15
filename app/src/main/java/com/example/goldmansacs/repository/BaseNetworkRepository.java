package com.example.goldmansacs.repository;

public class BaseNetworkRepository {

    public native String key();

    public BaseNetworkRepository() {
        System.loadLibrary("native-lib");
    }


}
