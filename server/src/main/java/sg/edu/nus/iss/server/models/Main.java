package sg.edu.nus.iss.server.models;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Main {
    private Double temp;
    private Double feelsLike;
    private Double tempMin;
    private Double tempMax;
    private Integer pressure;
    private Integer humidity;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public static Main jsonObjectToJavaObject(JsonObject o) {
        Main main = new Main();
        main.setTemp(o.getJsonNumber("temp").doubleValue());
        main.setFeelsLike(o.getJsonNumber("feels_like").doubleValue());
        main.setTempMin(o.getJsonNumber("temp_min").doubleValue());
        main.setTempMax(o.getJsonNumber("temp_max").doubleValue());
        main.setPressure(o.getJsonNumber("pressure").intValue());
        main.setHumidity(o.getJsonNumber("humidity").intValue());
        return main;
    }

    public JsonObjectBuilder toJsonObject() {
        return Json.createObjectBuilder().add("temp", this.getTemp())
                                            .add("feels_like", this.getFeelsLike())
                                            .add("temp_min", this.getTempMin())
                                            .add("temp_max", this.getTempMax())
                                            .add("pressure", this.getPressure())
                                            .add("humidity", this.getHumidity())
                                            ;
    }
}
