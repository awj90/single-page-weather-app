package sg.edu.nus.iss.server.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.json.Json;
import sg.edu.nus.iss.server.exceptions.WeatherException;
import sg.edu.nus.iss.server.models.Response;
import sg.edu.nus.iss.server.services.WeatherService;

@Controller
@RequestMapping(path="/api", produces=MediaType.APPLICATION_JSON_VALUE)
public class WeatherController {
    
    @Autowired
    private WeatherService weatherService;

    @GetMapping(path="/weather")
    @ResponseBody
    public ResponseEntity<String> getWeather(@RequestParam String city, @RequestParam(defaultValue = "metric") String units) throws WeatherException, IOException {
      try {
        Response r = weatherService.getWeather(city, units);
          return ResponseEntity.ok(r.toJsonObject().toString());
      } catch (WeatherException ex) {
        return ResponseEntity.status(400).body(Json.createObjectBuilder().add("error", ex.getMessage()).build().toString());
      }
      
    }
}
