import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ReminderService } from "../reminder/reminder.service";
import { Reminder } from "../reminder/reminder";
import { ConstantsService } from "../constants.service";
import { ThrowStmt } from "@angular/compiler";
import { HttpClient } from "@angular/common/http";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  reminder: Reminder = {
    id: 0,
    name: "",
    description: "",
    frequency: "",
    dateCreated: "",
    timeToRemind: ""
  };

  reminders: Reminder[] = [];
  currentReminder = null;
  currentIndex = -1;
  message = "";
  isLoading: boolean = false;
  visible: boolean = true;

  constructor(
      private http: HttpClient,
      private constants: ConstantsService,
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

  goToUpdate(id: string) {
      this.router.navigate(['/reminders/' + id]);
    }


  deleteReminder(reminder: Reminder): void {
    document.getElementById(reminder.id.toString()).remove();
    console.log(`deleted reminder: ${JSON.stringify(reminder)}`);
        this.reminderService.delete(reminder.id)
          .subscribe(
            response => {
              console.log(response);
              this.router.navigate(['/dashboard']);
            },
            error => {
              console.log(error);
            });
  }

  setActiveReminder(reminder, index): void {
      this.currentReminder = reminder;
      this.currentIndex = index;
    }

  updateReminder(reminder: Reminder): void {
        this.isLoading = true;
        this.reminderService.update(reminder.id, reminder)
          .subscribe(
            response => {
              this.isLoading = false;
              console.log(response);
              this.message = 'The reminder was updated successfully!';
            },
            error => {
              console.log(error);
              this.isLoading = false;
            });
    }

}
