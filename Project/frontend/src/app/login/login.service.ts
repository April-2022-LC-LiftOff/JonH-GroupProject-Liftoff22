import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { LoginUser } from "../login/loginuser";
import { ConstantsService } from "../constants.service";

@Injectable({
  providedIn: "root",
})
export class LoginService {
  constructor(private http: HttpClient, private constants: ConstantsService) {}

  getUser(loginuser: LoginUser): Observable<LoginUser> {
    return this.http.post<LoginUser>(
      this.constants.getRootURL() + "/login",
      loginuser
    );
  }
}
