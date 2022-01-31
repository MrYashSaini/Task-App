package com.example.test2fragment.Models;

public class mainRecipeModel {
    int pic;
    int pic2;
    int pic3;
    String text;

    public mainRecipeModel(int pic, int pic2,int pic3, String text) {
        this.pic = pic;
        this.pic2 = pic2;
        this.pic3 = pic3;
        this.text = text;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public int getPic2() {
        return pic2;
    }

    public void setPic2(int pic2) {
        this.pic2 = pic2;
    }
    public int getPic3() {
        return pic3;
    }

    public void setPic3(int pic3) {
        this.pic3 = pic3;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
