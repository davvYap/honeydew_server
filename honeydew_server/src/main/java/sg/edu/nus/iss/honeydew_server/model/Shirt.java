package sg.edu.nus.iss.honeydew_server.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Shirt {
    private String color;
    private String size;
    private Double price;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public JsonObjectBuilder toJSONObjectBuilder() {

        return Json.createObjectBuilder()
                .add("color", this.color)
                .add("size", this.size)
                .add("price", this.price);
    }

    public static Shirt createFromJSON(JsonObject jsObj) {
        Shirt shirt = new Shirt();
        String color = jsObj.getString("color").toUpperCase();
        shirt.setColor(color);
        shirt.setSize(jsObj.getString("size"));
        return shirt;
    }

}
