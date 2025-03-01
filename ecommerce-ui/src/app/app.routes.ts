import { Routes } from '@angular/router';
import { RegisterComponent } from './component/register/register.component';
import { LoginComponent } from './component/login/login.component';

export const routes: Routes = [
  {path:'', pathMatch:'full', component:LoginComponent},
  {path:'register',title: 'Register',component:RegisterComponent},
  {path:'login',title:'Login', component:LoginComponent}
];
