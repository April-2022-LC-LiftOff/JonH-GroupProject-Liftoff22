import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { Routes, RouterModule } from "@angular/router";
import { RegisterComponent } from "./register/register.component";
import { LoginComponent } from "./login/login.component";
import { PagenotfoundComponent } from "./pagenotfound/pagenotfound.component";
import { DashboardComponent } from "./dashboard/dashboard.component";
import { ReminderComponent } from "./reminder/reminder.component";
import { LandingComponent } from "./landing/landing.component";
import { UpdateReminderComponent } from "./update-reminder/update-reminder.component";
import { UpdateUserComponent } from './update-user/update-user.component';

const routes: Routes = [
  { path: "profile", component: UpdateUserComponent },
  { path: "reminders/:id", component: UpdateReminderComponent },
  { path: "dashboard", component: DashboardComponent },
  { path: "reminder", component: ReminderComponent },
  { path: "register", component: RegisterComponent },
  { path: "login", component: LoginComponent },
  { path: "landing", component: LandingComponent },
  { path: "", redirectTo: "/landing", pathMatch: "full" },
  { path: "**", component: PagenotfoundComponent }
];

@NgModule({
  //declarations: [],
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
