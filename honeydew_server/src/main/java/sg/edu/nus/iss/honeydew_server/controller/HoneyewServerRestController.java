package sg.edu.nus.iss.honeydew_server.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.honeydew_server.service.HoneydewServerService;

@RestController
@RequestMapping(path = "/api/quotation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class HoneyewServerRestController {
    @Autowired
    private HoneydewServerService honeySvc;

    @PostMapping
    public ResponseEntity<String> passQuotation(@RequestBody String json) throws IOException {
        return honeySvc.passItemPriceList(json);
    }

}
