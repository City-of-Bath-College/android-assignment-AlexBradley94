package com.example.bra11124543.santa;

/**
 * Created by bra11124543 on 16/12/2015.
 */
public class ReindeerObject {

    private String name;
    private Integer picture;
    private String noise;

    public ReindeerObject(String name, Integer picture, String noise) {
        this.name = name;
        this.picture = picture;
        this.noise = noise;
    }

    public String getName(){
        return name;
    }

    public Integer getPicture(){
        return picture;
    }

    public String getNoise(){
        return noise;
    }
}
