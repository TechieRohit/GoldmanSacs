package com.example.goldmansacs.repository;

public class BaseRepository {

    public native String key();

    public BaseRepository() {
        System.loadLibrary("native-lib");
    }


}
