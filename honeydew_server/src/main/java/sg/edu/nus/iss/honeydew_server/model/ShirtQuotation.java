package sg.edu.nus.iss.honeydew_server.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;

public class ShirtQuotation {
    private String id;

    private List<Shirt> shirts = new LinkedList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Shirt> getShirts() {
        return shirts;
    }

    public void setShirts(List<Shirt> shirts) {
        this.shirts = shirts;
    }

    public JsonObject toJSON() {
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        List<JsonObjectBuilder> listOfShirt = this.getShirts().stream()
                .map(s -> s.toJSONObjectBuilder())
                .toList();
        for (JsonObjectBuilder shirt : listOfShirt) {
            arrBuilder.add(shirt);
        }
        return Json.createObjectBuilder()
                .add("quoteId", this.id)
                .add("quotations", arrBuilder)
                .build();
    }

    public static ShirtQuotation createFromJSON(String json) throws IOException {
        ShirtQuotation sq = new ShirtQuotation();
        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonArray jsArr = r.readArray();
            List<Shirt> shirts = jsArr.stream()
                    .map(shirt -> (JsonObject) shirt)
                    .map(shirt -> Shirt.createFromJSON(shirt))
                    .toList();
            sq.setShirts(shirts);
        }
        return sq;
    }
}
