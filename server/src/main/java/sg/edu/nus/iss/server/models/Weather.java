package sg.edu.nus.iss.server.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Weather {

    private String main;
    private String description;
    private String icon;

    public String getMain() {
        return main;
    }
    public void setMain(String main) {
        this.main = main;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }

    public static Weather jsonObjectToJavaObject(JsonObject o) {
        Weather weather = new Weather();
        String m = o.getString("main");
        String d = o.getString("description");
        String i = o.getString("icon");
        weather.setMain(m);
        weather.setDescription(d);
        weather.setIcon("https://openweathermap.org/img/wn/%s@4x.png".formatted(i));
        return weather;
    }

    public JsonObject toJsonObject() {
        return Json.createObjectBuilder().add("main", this.getMain()).add("description", this.getDescription()).add("icon", this.getIcon()).build();
    }
}
