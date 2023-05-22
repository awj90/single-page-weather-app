import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';
import { Weather } from './models';

@Injectable()
export class WeatherService {
  http = inject(HttpClient);

  getWeather(city: string, units = 'metric'): Observable<Weather> {
    const params = new HttpParams().set('city', city).set('units', units); // spring-boot set up was both as @RequestParams
    return this.http.get<Weather>(`/api/weather`, { params });
  }
}
