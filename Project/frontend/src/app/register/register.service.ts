import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { User } from "../register/registeruser";

@Injectable({
  providedIn: "root",
})
export class RegisterService {
  constructor(private http: HttpClient) {}

  rootURL = "http://localhost:8080";

  addUser(user: User): Observable<User> {
    return this.http.post<User>(this.rootURL + "/register", user);
  }
}
