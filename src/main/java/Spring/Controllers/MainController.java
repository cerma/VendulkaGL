package Spring.Controllers;

import Spring.Model.Hodnoty;
import Spring.Services.MainServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Controller
public class MainController {
    @Autowired
    private MainServices mainServices;

@GetMapping()
    public String uvod(@ModelAttribute Hodnoty hodnota) throws IOException {
    mainServices.vypocet(hodnota);

    return "Uvod";

}
}
