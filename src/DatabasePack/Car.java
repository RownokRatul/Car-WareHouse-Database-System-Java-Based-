package DatabasePack;

import java.io.Serializable;

public class Car implements Serializable {
    private String registration;
    private String make;
    private String model;
    private int year;
    private String color;
    private int quantity;
    private String imagePath;
    private String editingBy;

    public void setRegistration(String reg) {
        registration = reg;
    }
    public void setMake(String mak) { make = mak; }
    public void setEditingBy(String edit) { editingBy = edit; }
    public void setModel(String mod) {
        model = mod;
    }
    public void setYear(int y) {
        year = y;
    }
    public void setColor(String c) {
        color = c;
    }
    public void setImagePath(String im) { imagePath = im; }
    public void setQuantity(int q) {
        quantity = q;
    }
    public String getRegistration() {
        return registration;
    }
    public String getMake() {
        return make;
    }
    public String getImagePath() { return imagePath; }
    public String getEditingBy() { return editingBy; }
    public String getModel() {
        return model;
    }
    public String getColor() {
        return color;
    }
    public int getYear() {
        return year;
    }
    public int getQuantity() {
        return quantity;
    }
}
