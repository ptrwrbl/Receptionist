package pollub.cs.ptrwrbl.receptionist.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HotelController {
    @RequestMapping({ "/hello" })
    public String welcomePage() {
        return "Welcome!";
    }
}