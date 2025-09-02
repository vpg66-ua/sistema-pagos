import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoadPagosComponent } from './components/load-pagos/load-pagos.component';
import { PagosComponent } from './components/pagos/pagos.component';
import { EstudiantesComponent } from './components/estudiantes/estudiantes.component';
import { LoadEstudiantesComponent } from './components/load-estudiantes/load-estudiantes.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { AdminTemplateComponent } from './components/admin-template/admin-template.component';
import { AuthGuard } from './guards/auth.guard';
import { AuthorizationGuard } from './guards/authorization.guard';
import { EstudianteDetailsComponent } from './components/estudiante-details/estudiante-details.component';
import { NewPagoComponent } from './components/new-pago/new-pago.component';

const routes: Routes = [
  {path: "", component:LoginComponent},
  {path: "login", component:LoginComponent},
  {path: "admin", component:AdminTemplateComponent,
    canActivate: [AuthGuard],
    children: [
    {path:"home", component:HomeComponent},
    {path:"profile", component:ProfileComponent},
    {path:"dashboard", component:DashboardComponent},
    {path:"load-estudiantes", component:LoadEstudiantesComponent, 
      canActivate: [AuthorizationGuard], data: {roles: ['ADMIN']}
    },
    {path:"estudiantes", component:EstudiantesComponent},
    {path:"pagos", component:PagosComponent},
    {
      path:"load-pagos", component:LoadPagosComponent, 
      canActivate: [AuthorizationGuard], data: {roles: ['ADMIN']}
    },
    { path: "estudiante-details/:codigo", component: EstudianteDetailsComponent},
    { path: "new-pago/:codigo", component: NewPagoComponent}
  ]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
