import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { Weather } from '../models';
import { Title } from '@angular/platform-browser';
import { WeatherService } from '../weather.service';

@Component({
  selector: 'app-weather',
  templateUrl: './weather.component.html',
  styleUrls: ['./weather.component.css'],
})
export class WeatherComponent implements OnInit {
  activatedRoute = inject(ActivatedRoute);
  title = inject(Title);
  weatherService = inject(WeatherService);
  weather$!: Observable<Weather>;
  city!: string;
  units!: string;

  ngOnInit(): void {
    this.city = this.activatedRoute.snapshot.params['city'];
    this.units = this.activatedRoute.snapshot.queryParams['units'] || 'metric'; // using short circuiting to assign a default value
    this.title.setTitle(`Current Weather at ${this.city}`);
    this.weather$ = this.weatherService.getWeather(this.city, this.units);
  }
}
