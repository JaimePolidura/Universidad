import {Component} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from "@angular/forms";
import {Subject} from "rxjs";
import {Router} from "@angular/router";
import {UsuariosService} from "../../api/users/usuarios.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  $loginState: Subject<LoginState> = new Subject<LoginState>();
  loginForm: FormGroup = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  constructor(
    private usersService: UsuariosService,
    private router: Router,
  ) { }

  login(): void{
    this.$loginState.next(LoginState.LOADING);

    this.usersService.login({
      nombre: this.username.value,
      claves: this.password.value
    }).subscribe(res => {
      this.router.navigateByUrl("/espaciotrabajos");
    },err => {
      this.$loginState.next(LoginState.ERROR);
      this.password.setValue('');
    });
  }

  get username(): AbstractControl { return <AbstractControl>this.loginForm.get('username'); }
  get password(): AbstractControl { return <AbstractControl>this.loginForm.get('password'); }
}

export enum LoginState {
  LOADING = "LOADING", SUCCESS = "SUCCESS", ERROR = "ERROR"
}
