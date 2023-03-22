package sg.edu.nus.iss.honeydew_server.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.honeydew_server.model.Shirt;
import sg.edu.nus.iss.honeydew_server.model.ShirtQuotation;

@Service
public class HoneydewServerService {

    public ResponseEntity<String> passItemPriceList(@RequestBody String json) throws IOException {
        ShirtQuotation sq = ShirtQuotation.createFromJSON(json);
        String id = generateId();
        sq.setId(id);
        for (Shirt shirt : sq.getShirts()) {
            double cost = calculatateShirtCost(shirt);
            shirt.setPrice(cost);
        }

        // deal with bad request
        if (json == null || sq.getShirts().isEmpty()) {
            JsonObject jsObj = Json.createObjectBuilder()
                    .add("error", "error message")
                    .build();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(jsObj.toString());
        }

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(sq.toJSON().toString());

    }

    public static String generateId() {
        String id = UUID.randomUUID().toString().substring(0, 6);
        return id;
    }

    public double calculatateShirtCost(Shirt shirt) {
        double cost = 0;
        String color = shirt.getColor().toLowerCase();
        switch (color) {
            case "yellow", "green":
                cost += 30.5;
                break;
            case "orange", "red":
                cost += 35;
                break;
            case "black", "blue":
                cost += 40.5;
                break;
        }
        switch (shirt.getSize()) {
            case "XS", "S":
                cost *= 1;
                break;
            case "M", "L":
                cost *= 1.2;
                break;
            case "XL", "XXL":
                cost *= 1.5;
                break;
            default:
                cost *= 1;
        }
        shirt.setPrice(cost);
        return cost;
    }
}
