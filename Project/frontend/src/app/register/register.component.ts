import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { RegisterService } from "./register.service";
import { User } from "./registeruser";

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"],
})
export class RegisterComponent implements OnInit {
  user: User = { username: "", email: "", mobile: "", carrier: "", password: "", verifyPassword: "" };
  isLoading: boolean = false;
  carriers = [
  {name: "AT&T"}, {name: "Sprint"}, {name: "T-Mobile"}, {name: "Verizon"}, {name: "Cricket"}
  ];

  constructor(
    private registerService: RegisterService,
    private router: Router
  ) {}

  ngOnInit() {}

  onClickSubmit(): void {
    this.isLoading = true;
    this.registerService.addUser(this.user).subscribe(
      (savedUser) => {
        console.log(`user saved: ${JSON.stringify(savedUser)}`);
        this.isLoading = false;
        this.router.navigate(["login"]);
      },
      (e) => {
        console.error("Error adding user " + JSON.stringify(e));
        this.isLoading = false;
      }
    );
  }
}
