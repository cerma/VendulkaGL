package Spring.Services;

import Spring.Model.Hodnoty;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class MainServices {



public Hodnoty vypocet(Hodnoty hodnota) throws IOException {


    List hodnoty = new ArrayList();

    URL oracle = new URL("https://vendarulez.herokuapp.com/api/v1/entries");
    BufferedReader in = new BufferedReader(
            new InputStreamReader(oracle.openStream()));

    String inputLine;
    while ((inputLine = in.readLine()) != null)
        hodnoty.add(inputLine);


    String vysledek = hodnoty.get(0).toString();
    String[] pole = vysledek.split("\t");



    double aktualniGL = Integer.parseInt(pole[2]);
    System.out.println(aktualniGL);

    double aktualniGLmmol = aktualniGL/18;
    int GLzaokrouhlene = (int) Math.round(aktualniGLmmol);

    hodnota.setVyslednaGL(GLzaokrouhlene);

    return null;
}


}
