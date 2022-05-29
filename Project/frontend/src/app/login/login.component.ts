import { Component, OnInit } from "@angular/core";
import { LoginService } from "./login.service";
import { LoginUser } from "./loginuser";

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
  errorBox = {eMessage: "" };
  nullUser = { id: 0, username: null, email: null, mobile: null, carrier: null, pwHash: null };
  badPasswordUser = { id: 0, username: "error", email: null, mobile: null, carrier: null, pwHash: null };

  constructor(private loginService: LoginService, private router: Router) {}

  onLoginSubmit(): void {
    this.isLoading = true;
    this.loginService.getUser(this.user).subscribe(
      (savedUser) => {
      if(Object.entries(savedUser).toString() === Object.entries(this.nullUser).toString()) {

            this.isLoading = false;
            this.errorBox.eMessage = "Username does not exist. Try again";
            this.router.navigate(["login"]);

       } else if(Object.entries(savedUser).toString() === Object.entries(this.badPasswordUser).toString()){
            this.isLoading = false;
            this.errorBox.eMessage = "Incorrect password. Try again";
            this.router.navigate(["login"]);
       }
        else {
            console.log(`user found: ${JSON.stringify(savedUser)}`);
            this.isLoading = false;
            this.router.navigate(["dashboard"]);
        }
      },
      (e) => {
        console.error("Error getting user " + JSON.stringify(e));
        this.isLoading = false;
      }
    );
  }
}
