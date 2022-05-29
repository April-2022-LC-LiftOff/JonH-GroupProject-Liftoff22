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
  {name: "Alltel"}, {name: "AT&T"}, {name: "Boost Mobile"},
  {name: "Cingular"}, {name: "Nextel"}, {name: "Sprint"}, 
  {name: "T-Mobile"}, {name: "Verizon"}, {name: "Virgin"},
  ];
  registerUserForm: FormGroup;
  errorBox = {eMessage: "" };
  nullUser = { id: 0, username: null, email: null, mobile: null, carrier: null, pwHash: null };

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
          });
  }

  onClickSubmit(): void {
    this.isLoading = true;
    this.user.username = this.registerUserForm.controls.username.value;
    this.user.email = this.registerUserForm.controls.email.value;
    this.user.password = this.registerUserForm.controls.password.value;
    this.user.verifyPassword = this.registerUserForm.controls.verifyPassword.value;
    this.user.mobile = this.normalize(this.registerUserForm.controls.mobile.value);
    this.user.carrier = this.registerUserForm.controls.carrier.value;
    this.registerService.addUser(this.user).subscribe(
      (savedUser) => {

        if(Object.entries(savedUser).toString() === Object.entries(this.nullUser).toString()) {

                console.log(`null user: ${JSON.stringify(this.nullUser)}`);
                this.isLoading = false;
                this.errorBox.eMessage = "User already exists";
                this.router.navigate(["register"]);
        } else {
                this.isLoading = false;
                this.router.navigate(["login"]);
        }

      },
      (e) => {
        this.errorBox.eMessage = "User already exists";
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
