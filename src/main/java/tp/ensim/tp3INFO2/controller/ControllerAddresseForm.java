package tp.ensim.tp3INFO2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerAddresseForm {

    @GetMapping("/adresseForm")
    public String showAddressForm() {
        return "adresse";
    }


}


