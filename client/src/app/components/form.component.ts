import { HttpClient } from '@angular/common/http';
import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Params, Router } from '@angular/router';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css'],
})
export class FormComponent implements OnInit {
  form!: FormGroup;
  fb = inject(FormBuilder);
  http = inject(HttpClient);
  router = inject(Router);

  ngOnInit(): void {
    this.form = this.fb.group({
      city: this.fb.control<string>('', [Validators.required]),
      units: this.fb.control<string>('metric', [Validators.required]),
    });
  }

  processForm() {
    const query = this.form.value;
    const params: Params = { units: query['units'] };
    // city as a path variable and units as a query parameter in the front end
    this.router.navigate(['/api/weather', query.city], { queryParams: params });
  }
}
