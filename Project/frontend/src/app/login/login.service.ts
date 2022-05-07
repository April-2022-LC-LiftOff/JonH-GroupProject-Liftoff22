import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { LoginUser } from "../login/loginuser";

@Injectable({
  providedIn: "root",
})
export class LoginService {
  constructor(private http: HttpClient) {}

  rootURL = "http://localhost:8080";

  getUser(loginuser: LoginUser): Observable<LoginUser> {
    return this.http.post<LoginUser>(this.rootURL + "/login", loginuser);
  }
}
