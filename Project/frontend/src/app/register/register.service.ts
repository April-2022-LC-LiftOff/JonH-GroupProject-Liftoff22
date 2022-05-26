import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { User } from "../register/registeruser";
import { ConstantsService } from "../constants.service";

const baseUrl = 'http://localhost:8080/api/profile';
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

  getUser(): Observable<User> {
      return this.http.get<User>(
        this.constants.getRootURL() + "/profile");
    }

update(data): Observable<any> {
    return this.http.put(`${baseUrl}`, data);
  }



}
