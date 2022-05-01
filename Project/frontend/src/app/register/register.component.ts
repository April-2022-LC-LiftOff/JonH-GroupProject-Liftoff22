import { Component, OnInit } from "@angular/core";
import { AppService } from "../config/config.service";

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"],
})
export class RegisterComponent implements OnInit {
  constructor(private appService: AppService) {}

  ngOnInit() {}
}
