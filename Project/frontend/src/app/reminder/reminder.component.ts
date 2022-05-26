import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { ReminderService } from "./reminder.service";
import { Reminder } from "./reminder";
import { RTime } from "./rTime";
import { ConstantsService } from "../constants.service";
import { ThrowStmt } from "@angular/compiler";
import { ErrorBoxComponent } from '../error-box/error-box.component';

@Component({
  selector: "app-reminder",
  templateUrl: "./reminder.component.html",
  styleUrls: ["./reminder.component.css"],
})
export class ReminderComponent implements OnInit {
  reminder: Reminder = {
    id: 0,
    name: "",
    description: "",
    frequency: "",
    dateCreated: "",
    timeToRemind: ""
  };

  isLoading: boolean = false;
  frequencies = [
    { id: 0, name: "Daily" },
    { id: 1, name: "Weekly" },
    { id: 2, name: "Monthly" }
  ];

  rTime: RTime = {
    hour: "",
    minute: "",
    meridiem: ""
  }

  hours = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'];

  minutes = ['00', '15', '30', '45'];

  meridiems = ['AM', 'PM'];

  reminders: Reminder[] = [];

  constructor(
    private reminderService: ReminderService,
    private router: Router,
    private constantsService: ConstantsService
  ) {}

  ngOnInit() { }

  gatherTime(): string {

     if (this.rTime.meridiem == 'PM') {
         this.rTime.hour = (parseInt(this.rTime.hour) + 12).toString(10);
     }
     return this.rTime.hour + ":" + this.rTime.minute + ":00";
  }

  onClickSubmit(): void {
    this.isLoading = true;
    this.reminder.timeToRemind = this.gatherTime();
    this.reminderService.addReminder(this.reminder).subscribe(
      (savedReminder) => {
        console.log(`reminder saved: ${JSON.stringify(savedReminder)}`);
        this.isLoading = false;
        this.reminders.push(savedReminder);
        this.router.navigate(["dashboard"]);
      },
      (e) => {
        console.error("Error adding reminder " + JSON.stringify(e));

        this.isLoading = false;
      }
    );
  }
}
