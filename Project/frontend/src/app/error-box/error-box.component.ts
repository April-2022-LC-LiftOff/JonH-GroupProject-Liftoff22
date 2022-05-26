import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-error-box',
  templateUrl: './error-box.component.html',
  styleUrls: ['./error-box.component.css']
})
export class ErrorBoxComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

//   displayError(): void {
//     let box = document.getElementById("errorBox");
//     if(box==null) {
//       console.log("box is null test");
//     } else {
//        box.style.visibility = "visible";
//        console.log(box.innerHTML);
//     }
//   }


}
