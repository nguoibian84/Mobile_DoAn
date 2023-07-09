package com.example.doan_oderfood;

import java.io.Serializable;

public class Drinks implements Serializable {
    public int image_Drink;
    public String name_Drink;

    public Drinks(int image_Drink, String name_Drink) {
        this.image_Drink = image_Drink;
        this.name_Drink = name_Drink;
    }

    public int getImage_Drink() {
        return image_Drink;
    }

    public void setImage_Drink(int image_Drink) {
        this.image_Drink = image_Drink;
    }

    public String getName_Drink() {
        return name_Drink;
    }

    public void setName_Drink(String name_Drink) {
        this.name_Drink = name_Drink;
    }
}
