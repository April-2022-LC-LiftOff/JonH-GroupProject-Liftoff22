import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { User } from "../register/User";

const baseUrl = "http://localhost:8080/";
@Injectable({
  providedIn: "root",
})
export class AppService {
  constructor(private http: HttpClient) {}

  rootURL = "http://localhost:8080";

  /* getAll(): Observable<any> {
    return this.http.get(baseUrl);
  }
  get(id): Observable<any> {
    return this.http.get(`${baseUrl}/${id}`);
  }
  create(data): Observable<any> {
    return this.http.post(baseUrl, data);
  }
  update(id, data): Observable<any> {
    return this.http.put(`${baseUrl}/${id}`, data);
  }
  delete(id): Observable<any> {
    return this.http.delete(`${baseUrl}/${id}`);
  }
  deleteAll(): Observable<any> {
    return this.http.delete(baseUrl);
  }
  findByTitle(title): Observable<any> {
    return this.http.get(`${baseUrl}?title=${title}`);
  } */

  addUser(user: User, id: number) {
    return this.http.post(this.rootURL + "/register", user);
  }
}
