import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import { ReminderService } from "./reminder.service";
import { Reminder } from "./reminder";
import { ConstantsService } from '../constants.service';

@Component({
  selector: 'app-reminder',
  templateUrl: './reminder.component.html',
  styleUrls: ['./reminder.component.css']
})

export class ReminderComponent implements OnInit {
    reminder: Reminder = { name: "", description: "", frequency: "", dateCreated: ""};
    isLoading: boolean = false;
    frequencies = [
        {id: 0, name: 'Daily'},
        {id: 1, name: 'Weekly'},
        {id: 2, name: 'Monthly'}
        ];

  constructor(
    private reminderService: ReminderService,
    private router: Router,
    private constantsService: ConstantsService
  ) {}

  ngOnInit() {}

  onClickSubmit(): void {
    this.isLoading = true;
    this.reminderService.addReminder(this.reminder).subscribe(
      (savedReminder) => {
        console.log(`reminder saved: ${JSON.stringify(savedReminder)}`);
        this.isLoading = false;
        this.router.navigate(["dashboard"]);
      },
      (e) => {
        console.error("Error adding reminder " + JSON.stringify(e));
        this.isLoading = false;
      }
    );
  }
}