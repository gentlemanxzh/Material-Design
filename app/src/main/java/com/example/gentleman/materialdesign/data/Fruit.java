package com.example.gentleman.materialdesign.data;

/**
 * 数据实体类
 * Created by gentleman on 2017/10/24.
 */

public class Fruit {
    private String name;
    private int imageId;


    public Fruit(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
