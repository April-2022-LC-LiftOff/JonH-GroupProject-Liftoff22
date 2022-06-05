import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RegisterService } from "../register/register.service";
import { ConstantsService } from "../constants.service";
import { ThrowStmt } from "@angular/compiler";
import {FormBuilder, ReactiveFormsModule, FormsModule, NgControl, FormGroup, FormControl} from '@angular/forms';

import { User } from "../register/registeruser";

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  user: User = { username: "", email: "", mobile: "", carrier: "", password: "", verifyPassword: "" };
  isLoading: boolean = false;
  carriers = [
    {name: "--Optional--"},
    {name: "Alltel"}, {name: "AT&T"}, {name: "Boost Mobile"},
    {name: "Cingular"}, {name: "Nextel"}, {name: "Sprint"}, 
    {name: "T-Mobile"}, {name: "Verizon"}, {name: "Virgin"},
    ];
  currentUser = null;
  message = "";
  updateUserForm: FormGroup;

  constructor(
        http: HttpClient,
        private constants: ConstantsService,
        private registerService: RegisterService,
        private router: Router,
        private route: ActivatedRoute,
        private constantsService: ConstantsService
  ) { }

  ngOnInit() {
    this.updateUserForm = new FormGroup({
         username: new FormControl(),
         email: new FormControl(),
         password: new FormControl(),
         verifyPassword: new FormControl(),
         mobile: new FormControl(),
         carrier: new FormControl()
       })
    this.message = '';
    const userObservable = this.registerService.getUser();
    userObservable.subscribe((userData: User) => {
          this.currentUser = userData;
          });
  }

updateUser(): void {
    this.isLoading = true;
    if (this.updateUserForm.controls.username.value != null) {
    this.currentUser.username = this.updateUserForm.controls.username.value;
    }
    if (this.updateUserForm.controls.email.value != null) {
        this.currentUser.email = this.updateUserForm.controls.email.value;
    }
    if (this.updateUserForm.controls.password.value != null) {
            this.currentUser.password = this.updateUserForm.controls.password.value;
        }
    if (this.updateUserForm.controls.verifyPassword.value != null) {
                this.currentUser.verifyPassword = this.updateUserForm.controls.verifyPassword.value;
            }
    if (this.updateUserForm.controls.mobile.value != null) {
                this.currentUser.mobile = this.normalize(this.updateUserForm.controls.mobile.value);
            }
    if (this.updateUserForm.controls.carrier.value != null) {
                    this.currentUser.carrier = this.updateUserForm.controls.carrier.value;
                }
    this.registerService.update(this.currentUser)
      .subscribe(
        response => {
          this.isLoading = false;
          console.log(response);
          this.router.navigate(["dashboard"]);
        },
        error => {
          console.log(error);
          this.isLoading = false;
        });
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
