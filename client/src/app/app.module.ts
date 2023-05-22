import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { WeatherComponent } from './components/weather.component';
import { FormComponent } from './components/form.component';
import { WeatherService } from './weather.service';

const appRoutes: Routes = [
  {
    path: '',
    component: FormComponent,
    title: 'Weather App',
  },
  { path: 'api/weather/:city', component: WeatherComponent },
  { path: '**', redirectTo: '/', pathMatch: 'full' },
];

@NgModule({
  declarations: [AppComponent, WeatherComponent, FormComponent],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
  ],
  providers: [WeatherService],
  bootstrap: [AppComponent],
})
export class AppModule {}
