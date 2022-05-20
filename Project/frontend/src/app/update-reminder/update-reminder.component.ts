import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReminderService } from "../reminder/reminder.service";
import { Reminder } from "../reminder/reminder";
import { ConstantsService } from "../constants.service";
import { ThrowStmt } from "@angular/compiler";
import { HttpClient } from "@angular/common/http";

@Component({
  selector: 'app-update-reminder',
  templateUrl: './update-reminder.component.html',
  styleUrls: ['./update-reminder.component.css']
})
export class UpdateReminderComponent implements OnInit {

      reminder: Reminder = {
        id: 0,
        name: "",
        description: "",
        frequency: "",
        dateCreated: "",
      };
      isLoading: boolean = false;
      frequencies = [
        { id: 0, name: "Daily" },
        { id: 1, name: "Weekly" },
        { id: 2, name: "Monthly" },
      ];

      reminders: Reminder[] = [];
      message = "";
      currentReminder = null;

  constructor(
  private http: HttpClient,
        private constants: ConstantsService,
        private reminderService: ReminderService,
        private router: Router,
        private route: ActivatedRoute,
        private constantsService: ConstantsService
  ) { }

  ngOnInit() {
      this.message = '';
      this.getReminder(this.route.snapshot.paramMap.get('id'));
  }

  getReminder(id): void {
      this.reminderService.get(id)
        .subscribe(
          data => {
            this.currentReminder = data;
            console.log(`current reminder: ${JSON.stringify(data)}`);
          },
          error => {
            console.log(error);
          });
    }

  updateReminder(): void {
      this.isLoading = true;
      this.reminderService.update(this.currentReminder.id, this.currentReminder)
        .subscribe(
          response => {
            this.isLoading = false;
            console.log(response);
            this.message = 'The reminder was updated successfully!';
            this.router.navigate(["dashboard"]);
          },
          error => {
            console.log(error);
            this.isLoading = false;
          });
  }
}
