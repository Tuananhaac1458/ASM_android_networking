package com.example.asm_android_networking.Repository;

public class Manga  {
    private String id;
    private String img;
    private String name;
    private int status;
    private String tacgia;
    private String[] categori;

    public Manga(){
    }


    public Manga(String id, String img, String name, int status, String tacgia, String[] categori) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.status = status;
        this.tacgia = tacgia;
        this.categori = categori;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String[] getCategori() {
        return categori;
    }

    public void setCategori(String[] categori) {
        this.categori = categori;
    }
}
