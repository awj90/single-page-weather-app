package sg.edu.nus.iss.server.exceptions;

public class WeatherException extends Exception{
        
    public WeatherException() {
        super();
    }

    public WeatherException(String message) {
        super(message);
    }
}
