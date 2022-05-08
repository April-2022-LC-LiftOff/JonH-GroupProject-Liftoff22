import { HttpClient } from "@angular/common/http";
import { ThrowStmt } from "@angular/compiler";
import { Component, OnInit } from "@angular/core";
import { Observable } from "rxjs";
import { ConstantsService } from "../constants.service";

@Component({
  selector: "app-logout",
  templateUrl: "./logout.component.html",
  styleUrls: ["./logout.component.css"],
})
export class LogoutComponent implements OnInit {
  constructor(private http: HttpClient, private constants: ConstantsService) {}

  ngOnInit() {}

  logOut(): void {
    console.log("Logging out.");
    const newLocal: Observable<void> = this.http.post<void>(
      this.constants.getRootURL() + "/logout",
      null
    );

    newLocal.subscribe(
      () => {
        console.log("Logged out.");
      },
      (error) => {}
    );
  }
}
