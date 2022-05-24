import { Component, OnInit } from "@angular/core";
import { LoginService } from "./login.service";
import { LoginUser } from "./loginuser";
import { ErrorBoxComponent } from '../error-box/error-box.component';

import { Router } from "@angular/router";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
})
export class LoginComponent {
  user: LoginUser = {
    username: "",
    password: "",
  };
  isLoading: boolean = false;

  constructor(private loginService: LoginService, private router: Router) {}

  onLoginSubmit(): void {
    this.isLoading = true;
    this.loginService.getUser(this.user).subscribe(
      (savedUser) => {
        console.log(`user found: ${JSON.stringify(savedUser)}`);
        this.isLoading = false;
        this.router.navigate(["dashboard"]);
      },
      (e) => {
        console.error("Error getting user " + JSON.stringify(e));
//         let box = document.getElementById("errorBox");
//         if(box==null) {
//             console.log("box is null test");
//         } else {
//             box.style.visibility = "visible";
//             console.log(box.innerHTML);
//         }
        ErrorBoxComponent.displayError();
        this.isLoading = false;
      }
    );
  }
}
