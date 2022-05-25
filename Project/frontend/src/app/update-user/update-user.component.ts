import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RegisterService } from "../register/register.service";
import { ConstantsService } from "../constants.service";
import { ThrowStmt } from "@angular/compiler";

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
  {name: "AT&T"}, {name: "Sprint"}, {name: "T-Mobile"}, {name: "Verizon"}, {name: "Cricket"}
  ];
  currentUser = null;
  message = "";

  constructor(
        http: HttpClient,
        private constants: ConstantsService,
        private registerService: RegisterService,
        private router: Router,
        private route: ActivatedRoute,
        private constantsService: ConstantsService
  ) { }

  ngOnInit() {
    this.message = '';
    const userObservable = this.registerService.getUser();
    userObservable.subscribe((userData: User) => {
          this.currentUser = userData;
          console.log(`current user: ${JSON.stringify(this.currentUser)}`);
          });
  }

updateUser(): void {
    this.isLoading = true;
    this.currentUser.mobile = this.normalize(this.currentUser.mobile);
    console.log(`current time: ${JSON.stringify(this.currentUser.mobile)}`);
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
