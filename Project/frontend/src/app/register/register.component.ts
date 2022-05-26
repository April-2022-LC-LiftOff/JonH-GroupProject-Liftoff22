import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { RegisterService } from "./register.service";
import { User } from "./registeruser";
import {FormBuilder, ReactiveFormsModule, FormsModule, NgControl, FormGroup, FormControl, Validators} from '@angular/forms';
import { ConfirmedValidator } from './confirmedValidator';
@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"],
})
export class RegisterComponent implements OnInit {
  user: User = { username: "", email: "", mobile: "", carrier: "", password: "", verifyPassword: "" };
  isLoading: boolean = false;
  carriers = [
  {name: "--Optional--"},
  {name: "AT&T"}, {name: "Sprint"}, {name: "T-Mobile"}, {name: "Verizon"}, {name: "Cricket"}
  ];
  registerUserForm: FormGroup;

  constructor(
    private registerService: RegisterService,
    private router: Router,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
  this.registerUserForm = this.fb.group({
           username: ["", [Validators.required, Validators.minLength(3), Validators.maxLength(15)]],
           email: ["", [Validators.required, Validators.email]],
           password: ["", [Validators.required, Validators.minLength(5), Validators.maxLength(25)]],
           verifyPassword: ["", [Validators.required]],
           mobile: [""],
           carrier: [""]
          }, { 
            validator: ConfirmedValidator('password', 'verifyPassword')
          })
  }

  onClickSubmit(): void {
    this.isLoading = true;
    this.user.username = this.registerUserForm.controls.username.value;
    this.user.email = this.registerUserForm.controls.email.value;
    this.user.password = this.registerUserForm.controls.password.value;
    this.user.verifyPassword = this.registerUserForm.controls.verifyPassword.value;
    this.user.mobile = this.normalize(this.registerUserForm.controls.mobile.value);
    this.user.carrier = this.registerUserForm.controls.carrier.value;
    console.log(`user: ${JSON.stringify(this.user)}`);
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

  normalize(phone: string): string {
      const regex = /^(-)|[^0-9\n]+/gm;
      const subst = ``;

      // The substituted value will be contained in the result variable
      const result = phone.replace(regex, subst);
      //normalize string and remove all unnecessary characters

      //check if number length equals to 10
      if (result.length == 10) {
          //reformat and return phone number
          return result;
      }

      return null;
  }
}
