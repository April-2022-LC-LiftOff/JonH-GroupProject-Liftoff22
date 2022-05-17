import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppRoutingModule } from "./app-routing.module";
import { AppComponent } from "./app.component";
import { RegisterComponent } from "./register/register.component";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { LoginComponent } from "./login/login.component";
import { PagenotfoundComponent} from "./pagenotfound/pagenotfound.component";
import { HomeComponent } from './home/home.component';
import { ReminderComponent } from './reminder/reminder.component';
import { LandingComponent } from "./landing/landing.component";
import { LogoutComponent } from "./logout/logout.component";
import { SimpleTableComponent } from './simple-table/simple-table.component';
import { DashboardComponent } from './dashboard/dashboard.component';


@NgModule({
  declarations: [
  AppComponent, RegisterComponent,
  LoginComponent, PagenotfoundComponent,
  ReminderComponent, LandingComponent,
  LogoutComponent,
  SimpleTableComponent,
  DashboardComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}

