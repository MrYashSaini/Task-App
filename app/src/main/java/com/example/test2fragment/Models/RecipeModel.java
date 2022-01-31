package com.example.test2fragment.Models;

public class RecipeModel {
    int pic;
    int pic2;
    String text;

    public RecipeModel(int pic,int pic2, String text) {
        this.pic = pic;
        this.pic2 = pic2;
        this.text = text;

    }

    public int getPic2() {
        return pic2;
    }

    public void setPic2(int pic2) {
        this.pic2 = pic2;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getText() {
        return text;
    }


    public void setText(String text) {
        this.text = text;
    }
}
