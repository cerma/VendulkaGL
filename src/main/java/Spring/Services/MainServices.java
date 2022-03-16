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
private  String[] pole;
private int GLzaokrouhlene;


public void vypocet(Hodnoty hodnota) throws IOException {


    List hodnoty = new ArrayList();

    URL oracle = new URL("https://vendarulez.herokuapp.com/api/v1/entries");
    BufferedReader in = new BufferedReader(
            new InputStreamReader(oracle.openStream()));

    String inputLine;
    while ((inputLine = in.readLine()) != null)
        hodnoty.add(inputLine);


    String vysledek = hodnoty.get(0).toString();
    pole = vysledek.split("\t");



    double aktualniGL = Integer.parseInt(pole[2]);
    System.out.println(aktualniGL);

    double aktualniGLmmol = aktualniGL/18;
    GLzaokrouhlene = (int) Math.round(aktualniGLmmol);
    hodnota.setVyslednaGLhigh(null);
    hodnota.setVyslednaGL(null);
    hodnota.setVyslednaGLlow(null);


    if (GLzaokrouhlene <=4){
        hodnota.setVyslednaGLlow(GLzaokrouhlene);
    }
    else if(GLzaokrouhlene > 4 && GLzaokrouhlene <=8){
        hodnota.setVyslednaGL(GLzaokrouhlene);
    }
    else if(GLzaokrouhlene > 8){
        hodnota.setVyslednaGLhigh(GLzaokrouhlene);

    }

   // hodnota.setVyslednaGL(GLzaokrouhlene);


}
//smajlici
public void smajlik(Hodnoty hodnota){
String trend = pole[3];

if ((trend.contains("Down") || trend.contains("Flat")) && GLzaokrouhlene<6){
    hodnota.setObrazek("low");
}
else if ((GLzaokrouhlene==7 || GLzaokrouhlene==6)&&(trend.contains("Down") || (trend.contains("Flat")))){
        hodnota.setObrazek("medium");
    }


 else if ((GLzaokrouhlene<=9 && GLzaokrouhlene>7) || (trend.contains("Up") && GLzaokrouhlene <=8)) {
        hodnota.setObrazek("ok");
    }
 else
     hodnota.setObrazek("high");





}



}
