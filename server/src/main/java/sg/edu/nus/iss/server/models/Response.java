package sg.edu.nus.iss.server.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Response {
    private Main main;
    private String name;
    private List<Weather> weather;

    public Main getMain() {
        return main;
    }
    public void setMain(Main main) {
        this.main = main;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Weather> getWeather() {
        return weather;
    }
    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public static Response jsonStringToJsonObject(String json) throws IOException {
        Response response = new Response();

        if (json!=null) {
            InputStream is = new ByteArrayInputStream(json.getBytes()); 
            JsonReader jsonReader = Json.createReader(is);
            JsonObject jsonObject = jsonReader.readObject();
            
            response.setName(jsonObject.getString("name"));
            response.setMain(Main.jsonObjectToJavaObject(jsonObject.getJsonObject("main")));

            JsonArray jsonArray = jsonObject.getJsonArray("weather");
            List<Weather> weathers = jsonArray.stream().map(v -> v.asJsonObject())
			.map(o -> Weather.jsonObjectToJavaObject(o))
			.toList();
            response.setWeather(weathers);
        }
        return response;
    }

    public JsonObject toJsonObject() {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (Weather w: this.getWeather()) {
            jsonArrayBuilder.add(w.toJsonObject());
        }
        return Json.createObjectBuilder()
                    .add("name", this.getName())
                    .add("main", this.getMain().toJsonObject())
                    .add("weather", jsonArrayBuilder.build())
                    .build();
    }

}
