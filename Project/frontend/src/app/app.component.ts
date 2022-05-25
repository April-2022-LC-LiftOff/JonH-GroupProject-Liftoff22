import { Component, EventEmitter } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, ReactiveFormsModule, FormsModule, NgControl } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'demo reminder-app';
  visible: boolean = true;
}
