package tp.ensim.tp3INFO2.controller;
// WeatherController.java

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import tp.ensim.tp3INFO2.model.AddressResponse;
import tp.ensim.tp3INFO2.model.WeatherResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class meteoController {
    private final String etalabApiUrl = "https://api-adresse.data.gouv.fr/search/";
    private final String meteoApiUrl = "https://api.meteo-concept.com/api/forecast/daily?token=62c71292da325a81472f1e1db06234438a3132a71acf91deafcb1f1998b8f08d";
    @GetMapping("/meteo")
    public String getWeather(String address) {

        return "meteo";
    }
    @PostMapping("/meteo")
    public String getWeather(@RequestParam("address") String address, Model model) {
        model.addAttribute("address", address);


        try {
            // Appel de l'API Etalab Adresse
            RestTemplate restTemplate = new RestTemplate();
            AddressResponse adresseResponse = restTemplate.getForObject(etalabApiUrl + "?q=" + address, AddressResponse.class);
            AddressResponse.Properties properties = adresseResponse.getFeatures()[0].getProperties();

            String city = adresseResponse.getFeatures()[0].getProperties().getCity();          //récupérer la ville de l'adresse (on prend uniquement la première ville renvoyé par l'API. Il n'y a pas de vérification de l'adresse avant)
            double [] coor = adresseResponse.getFeatures()[0].getGeometry().getCoordinates();  //récupérer les coordonnées géographiques de l'adresse
            model.addAttribute("city", city);

            // Ajouter les résultats à l'objet Model pour les afficher dans le template
            model.addAttribute("address", properties.getLabel());
            model.addAttribute("city", properties.getCity());
            //model.addAttribute("coordinates", adresseResponse.getFeatures()[1].getCoordinates());

            // Call the second API (Meteo Concept) with longitude and latitude
            String meteoUrlWithCoords = meteoApiUrl + "&latlng=" + coor[1] + "," + coor[0];
            WeatherResponse weatherResponse = restTemplate.getForObject(meteoUrlWithCoords, WeatherResponse.class);

            LocalDate date = LocalDate.now();       //récupérer la date d'aujourd'hui
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");    //Permet de formatter la date sous la forme jour mois année





            //on récupère les prévisions météo de chaque jour pour les envoyer au template
            for (int i = 0; i < weatherResponse.getForecast().length; i++) {
                int weather_code, tmin, tmax, probarain,wind10m;

                weather_code = weatherResponse.getForecast()[i].getWeather();     //code météo du jour
                tmin = weatherResponse.getForecast()[i].getTmin();                //température minimale
                tmax = weatherResponse.getForecast()[i].getTmax();                //température maximale
                probarain = weatherResponse.getForecast()[i].getProbarain();      //probabilité de pluie en %
                wind10m = weatherResponse.getForecast()[i].getWind10m();

                //envoyer chaque attribut au template (le numéro _i permet de les différencier)
                model.addAttribute("weather_" + i, weather_code);
                model.addAttribute("tmin_" + i, tmin);
                model.addAttribute("tmax_" + i, tmax);
                model.addAttribute("probarain_" + i, probarain);
                model.addAttribute("wind10m" + i, wind10m);
                model.addAttribute("date_" + i, date.plusDays(i).format(formatter));    //.format(formatter) permet d'envoyer la date au formattage défini précédement
                model.addAttribute("icon_" + i, weatherIconConverter(weather_code));

            }

            // Retourner le nom du template Thymeleaf à afficher
            model.addAttribute("adresseResponse", adresseResponse);

            // Add weather information to the model
            model.addAttribute("weather", weatherResponse);

            return "meteo";
        }catch(HttpClientErrorException.BadRequest e) {
            // Gérer spécifiquement les erreurs 400 Bad Request de l'API Etalab Adresse
            model.addAttribute("error", "Invalid address. Please enter a valid address.");
            return "meteo";
        } catch (Exception e) {
            // Gérer d'autres exceptions ici si nécessaire
            model.addAttribute("error", "An error occurred. Please try again later.");
            return "meteo";
        }
    }

    String weatherIconConverter(int weatherCode){
        //ensoleillé
        if (weatherCode<= 3){
            return "sunny.svg";
        }
        //nuageux
        else if (weatherCode>=4 && weatherCode<=7){
            return "cloudy.svg";
        }
        //pluie
        else if ((weatherCode>=10 && weatherCode<=16) || (weatherCode>=40 && weatherCode<=48) || (weatherCode>=210 && weatherCode<=212) || weatherCode==235){
            return "rainy.svg";
        }
        //neige
        else if ((weatherCode>=20 && weatherCode<=32) || (weatherCode>=60 && weatherCode<=78) || (weatherCode>=220 && weatherCode<=232)){
            return "snowy.svg";
        }
        //orage
        else if (weatherCode>=100 && weatherCode<=142){
            return "thunder.svg";
        }
        else return "";
    }

}