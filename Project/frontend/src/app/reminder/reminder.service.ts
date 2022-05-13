import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Reminder } from "../reminder/reminder";
import { ConstantsService } from "../constants.service";

@Injectable({
  providedIn: "root",
})
export class ReminderService {
  constructor(private http: HttpClient, private constants: ConstantsService) {}

  addReminder(reminder: Reminder): Observable<Reminder> {
    return this.http.post<Reminder>(
      this.constants.getRootURL() + "/reminder",
      reminder
    );
  }
}