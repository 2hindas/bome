package org.oopp.server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {
    /**
     * This function sets the response for a GET request on the /hello URL.
     * It responds with a Message object, containing the String "Hello World!".
     *
     * @return A ResponseEntity with a fitting Message.
     */
    @GetMapping(value = "/hello")
    public ResponseEntity<Message> respond() {
        System.out.println("Get request!");
        Message msg = new Message(Codes.HELLO_WORLD, "no u");
        return ResponseEntity.accepted().body(msg);
    }

}
