import { Component } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'demo reminder-app';
  visible: boolean = true;
  hideUtility() {
  this.visible = this.visible?false:true;
  }
}
