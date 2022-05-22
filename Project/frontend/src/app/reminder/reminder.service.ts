import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Reminder } from "../reminder/reminder";
import { ConstantsService } from "../constants.service";

const baseUrl = 'http://localhost:8080/api/reminders';
@Injectable({
  providedIn: "root",
})
export class ReminderService {
  constructor(private http: HttpClient, private constants: ConstantsService ) {}

  getAllReminders(): Observable<Reminder[]> {
    return this.http.get<Reminder[]>(
      this.constants.getRootURL() + "/reminders");
  }

  get(id): Observable<any> {
      return this.http.get(`${baseUrl}/${id}`);
    }

  addReminder(reminder: Reminder): Observable<Reminder> {
      return this.http.post<Reminder>(
        this.constants.getRootURL() + "/reminder",
        reminder
      );
    }

  delete(id): Observable<any> {
      return this.http.delete(`${baseUrl}/${id}`);
    }

  update(id, data): Observable<any> {
      return this.http.put(`${baseUrl}/${id}`, data);
    }



}