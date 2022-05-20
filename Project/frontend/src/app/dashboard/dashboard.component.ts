import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ReminderService } from "../reminder/reminder.service";
import { Reminder } from "../reminder/reminder";
import { ConstantsService } from "../constants.service";
import { ThrowStmt } from "@angular/compiler";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
    reminder: Reminder = {
      name: "",
      description: "",
      frequency: "",
      dateCreated: "",
    };
  reminders: Reminder[] = [];

  constructor(
      private reminderService: ReminderService,
      private router: Router,
      private constantsService: ConstantsService
      ) { }

  ngOnInit() {
   const remindersObservable = this.reminderService.getAllReminders();
            remindersObservable.subscribe((remindersData: Reminder[]) => {
                this.reminders = remindersData;
            });
  }

  goToAddReminder(pageName:string) {
    this.router.navigate([pageName]);
  }

}
