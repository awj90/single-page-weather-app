## Set/Export env variables

- OPEN_WEATHER_API_URL = https://api.openweathermap.org/data/2.5/weather
- OPEN_WEATHER_API_KEY = {your_api_key}

## GET /api/weather?city={cityname}&units={units}

- Specifying a city name is required
- Default units if not specified is in metrics

## Server processes response from API and provides only part of the API response to Angular client

- Example server response json:
  {
  "name": "Singapore",
  "main": {
  "temp": 32.23,
  "feels_like": 37.94,
  "temp_min": 30.92,
  "temp_max": 33.47,
  "pressure": 1009,
  "humidity": 61
  },
  "weather": [
  {
  "main": "Clouds",
  "description": "broken clouds",
  "icon": "https://openweathermap.org/img/wn/04d@4x.png"
  }
  ]
  }