import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { User } from "../register/registeruser";
import { ConstantsService } from "../constants.service";

@Injectable({
  providedIn: "root",
})
export class RegisterService {
  constructor(private http: HttpClient, private constants: ConstantsService) {}

  addUser(user: User): Observable<User> {
    return this.http.post<User>(
      this.constants.getRootURL() + "/register",
      user
    );
  }
}
