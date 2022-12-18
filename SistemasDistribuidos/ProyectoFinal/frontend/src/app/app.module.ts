import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { NavbarComponent } from './navbar/navbar.component';
import { EspacioTrabajosComponent } from './espaciotrabajos/espacio-trabajos.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {ReactiveFormsModule} from "@angular/forms";
import {HttpRequestAuthenticationInterceptor} from "../api/http-request-authentication.interceptor";
import { EspacioTrabajoComponent } from './espaciotrabajos/espacio-trabajo/espacio-trabajo.component';
import {LoadingSpinnerComponent} from "./_shared/components/loading-spinner/loading-spinner.component";
import {FocusDirective} from "./_shared/directives/focus.directive";
import { ArchivosComponent } from './archivos/archivos.component';
import { ArchivoComponent } from './archivos/archivo/archivo.component';
import { ArchivoRutaNavegacionComponent } from './archivos/archivo-ruta-navegacion/archivo-ruta-navegacion.component';
import {InputNameModalComponent} from "./_shared/components/input-name-modal/input-name-modal.component";
import {ToastrModule} from "ngx-toastr";
import { DateTimePipe } from './_shared/pipes/date-time.pipe';
import {NgbDropdownModule, NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ConfirmationModalComponent} from "./_shared/components/confirmation-modal/confirmation-modal.component";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NavbarComponent,
    EspacioTrabajosComponent,
    EspacioTrabajoComponent,
    LoadingSpinnerComponent,
    FocusDirective,
    ArchivosComponent,
    ArchivoComponent,
    InputNameModalComponent,
    ArchivoRutaNavegacionComponent,
    ConfirmationModalComponent,
    DateTimePipe
  ],
  imports: [
    NgbModule,
    BrowserModule,
    NgbDropdownModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    ToastrModule.forRoot(),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpRequestAuthenticationInterceptor,
      multi: true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
