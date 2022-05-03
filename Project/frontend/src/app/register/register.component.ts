import { Component, OnInit } from "@angular/core";
import { RegisterService } from "./register.service";
import { User } from "./registeruser";

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"],
})
export class RegisterComponent implements OnInit {
  user: User = { username: "", email: "", password: "", verifyPassword: "" };
  isLoading: boolean = false;

  constructor(private registerService: RegisterService) {}

  ngOnInit() {}

  onClickSubmit(): void {
    this.isLoading = true;
    this.registerService.addUser(this.user).subscribe(
      (savedUser) => {
        console.log(`user saved: ${JSON.stringify(savedUser)}`);
        this.isLoading = false;
      },
      (e) => {
        console.error("Error adding user " + JSON.stringify(e));
        this.isLoading = false;
      }
    );
  }
}
