package com.example.asm_android_networking.Repository;

public class Manga  {
    private String id;
    private String img;
    private String name;
    private String status;
    private String tacgia;
    private String categori;

    public Manga(){
    }



    public Manga(String id,String img, String name, String status, String tacgia, String categori) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.status = status;
        this.tacgia = tacgia;
        this.categori = categori;
    }

    public String getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getTacgia() {
        return tacgia;
    }

    public String getCategori() {
        return categori;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public void setCategori(String categori) {
        this.categori = categori;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
