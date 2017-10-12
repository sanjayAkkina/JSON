package com.example.android.json;

/**
 * Created by Sanjay on 10/12/17.
 */

public class Categories {
    String categoryZero = "";
    String categoryOne = "";
    String categoryTwo = "";
    String categoryThree = "";
    String categoryFour = "";
    String categoryFive = "";
    int counter = 0;

    public Categories() {}

    public Categories(String choice) {
        if (counter == 0)
        {
            setCategoryZero(choice);
            counter++;
        } else if (counter == 1) {
            setCategoryOne(choice);
            counter++;
        } else if (counter == 2) {
            setCategoryTwo(choice);
            counter++;
        } else if (counter == 3) {
            setCategoryThree(choice);
            counter++;
        } else if (counter == 4) {
            setCategoryFour(choice);
            counter++;
        } else if (counter == 5) {
            setCategoryFive(choice);
            counter++;
        }
    }

    public String getCategoryZero() {
        return categoryZero;
    }

    public void setCategoryZero(String categoryZero) {
        this.categoryZero = categoryZero;
    }

    public String getCategoryOne() {
        return categoryOne;
    }

    public void setCategoryOne(String categoryOne) {
        this.categoryOne = categoryOne;
    }

    public String getCategoryTwo() {
        return categoryTwo;
    }

    public void setCategoryTwo(String categoryTwo) {
        this.categoryTwo = categoryTwo;
    }

    public String getCategoryThree() {
        return categoryThree;
    }

    public void setCategoryThree(String categoryThree) {
        this.categoryThree = categoryThree;
    }

    public String getCategoryFour() {
        return categoryFour;
    }

    public void setCategoryFour(String categoryFour) {
        this.categoryFour = categoryFour;
    }

    public String getCategoryFive() {
        return categoryFive;
    }

    public void setCategoryFive(String categoryFive) {
        this.categoryFive = categoryFive;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "categoryZero='" + categoryZero + '\'' +
                ", categoryOne='" + categoryOne + '\'' +
                ", categoryTwo='" + categoryTwo + '\'' +
                ", categoryThree='" + categoryThree + '\'' +
                ", categoryFour='" + categoryFour + '\'' +
                ", categoryFive='" + categoryFive + '\'' +
                ", counter=" + counter +
                '}';
    }
}
