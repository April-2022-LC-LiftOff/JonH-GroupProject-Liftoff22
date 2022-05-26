import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { RegisterComponent } from "./register/register.component";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { LoginComponent } from "./login/login.component";
import { PagenotfoundComponent } from "./pagenotfound/pagenotfound.component";
import { HomeComponent } from "./home/home.component";
import { ReminderComponent } from "./reminder/reminder.component";
import { LandingComponent } from "./landing/landing.component";
import { LogoutComponent } from "./logout/logout.component";
import { DashboardComponent } from "./dashboard/dashboard.component";
import { UpdateReminderComponent } from "./update-reminder/update-reminder.component";

import { NavbarComponent } from "./navbar/navbar.component";
import { UpdateUserComponent } from './update-user/update-user.component';
import { PhoneMaskDirective } from './update-user/phone-mask-directive';


@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    LoginComponent,
    PagenotfoundComponent,
    ReminderComponent,
    LandingComponent,
    LogoutComponent,
    DashboardComponent,
    UpdateReminderComponent,
    NavbarComponent,
    UpdateUserComponent,
    PhoneMaskDirective
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  exports: [
    PhoneMaskDirective
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
