import { Component, OnInit } from "@angular/core";
import { LoginService } from "./login.service";
import { LoginUser } from "./loginuser";

import { Router, ActivatedRoute, ParamMap } from "@angular/router";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
})
export class LoginComponent implements OnInit {
  user: LoginUser = {
    username: "",
    password: "",
  };
  isLoading: boolean = false;

  constructor(
    private loginService: LoginService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.route.queryParams.subscribe((params) => {
      this.loginService = params["login"];
    });
  }

  onLoginSubmit(): void {
    this.isLoading = true;
    this.loginService.getUser(this.user).subscribe(
      (savedUser) => {
        console.log(`user found: ${JSON.stringify(savedUser)}`);
        this.isLoading = false;
      },
      (e) => {
        console.error("Error getting user " + JSON.stringify(e));
        this.isLoading = false;
      }
    );
  }
}
