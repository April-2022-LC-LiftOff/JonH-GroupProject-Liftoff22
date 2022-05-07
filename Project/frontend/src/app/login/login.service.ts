import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { loginUser } from "../login/loginuser";

@Injectable({
  providedIn: "root",
})
export class LoginService {
  constructor(private http: HttpClient) {}

  rootURL = "http://localhost:8080";

  getUser(loginuser: loginUser): Observable<loginUser> {
    return this.http.post<loginUser>(this.rootURL + "/login", loginuser);
  }
}
