package sg.edu.nus.iss.server.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sg.edu.nus.iss.server.exceptions.WeatherException;
import sg.edu.nus.iss.server.models.Response;

@Service
public class WeatherService {
    
    @Value("${open.weather.api.url}")
    private String openWeatherUrl;

    @Value("${open.weather.api.key}")
    private String openWeatherApiKey;

    public Response getWeather(String city, String units) throws WeatherException, IOException {
        // construct full URL
        // city.replaceAll spaces with + for cities with two words (eg. Buenos Aires or Hong Kong)
        String fullUrl = UriComponentsBuilder
                            .fromUriString(openWeatherUrl)
                            .queryParam("q", city.replaceAll(" ", "+"))
                            .queryParam("units", units)
                            .queryParam("appid", openWeatherApiKey)
                            .toUriString();

         // instantiate RestTemplate
         RestTemplate restTemplate = new RestTemplate();

        // alternatively by exchange
        // RequestEntity req = RequestEntity.get(fullUrl).build();
        //  try {
        //     ResponseEntity<String> resp = restTemplate.exchange(req, String.class);
        //  } catch (RestClientException ex) {
        //     throw new WeatherException(ex.getMessage());
        //  }

        // must do error handling
        // RestClientException is thrown when response status code is not 200s/300s
        try {
            ResponseEntity<String> resp = restTemplate.getForEntity(fullUrl, String.class);
            Response r = Response.jsonStringToJsonObject(resp.getBody());
            return r;
        } catch (RestClientException ex) {
            throw new WeatherException(ex.getMessage());
        }
 
        //  Response r = Response.jsonStringToJsonObject(resp.getBody());
        //  if (r!=null) {
        //      return Optional.of(r);
        //  } else {
        //      return Optional.empty();
        //  }
    }
}
