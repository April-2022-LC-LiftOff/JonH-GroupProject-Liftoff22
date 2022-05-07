import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class lll {
  constructor(private http: HttpClient) {}

  rootURL = "http://localhost:8080";


  }
}

//work on this later