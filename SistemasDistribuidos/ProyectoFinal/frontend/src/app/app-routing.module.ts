import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {EspacioTrabajosComponent} from "./espaciotrabajos/espacio-trabajos.component";
import {NeedsToBeAuthenticatedGuard} from "./_shared/guards/needs-to-be-authenticated.guard";
import {ArchivosComponent} from "./archivos/archivos.component";

const routes: Routes = [
  {path: "", component: EspacioTrabajosComponent, canActivate: [NeedsToBeAuthenticatedGuard]},
  {path: "espaciotrabajos", component: EspacioTrabajosComponent, canActivate: [NeedsToBeAuthenticatedGuard]},
  {path: "archivos/:espaciotrabajoid", component: ArchivosComponent, canActivate: [NeedsToBeAuthenticatedGuard]},
  {path: "login", component: LoginComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
