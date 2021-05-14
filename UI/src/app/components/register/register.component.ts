import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../service/auth/authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  error: string = "";
  registerForm: FormGroup;
  loading = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
    // if we are logged in there is no reason to stay on /register
    if (this.authenticationService.isAuthenticated) {
      this.router.navigate(['/']);
    }
  }


  // convenience
  get username() {
    return this.registerForm.controls.username;
  }

  // convenience
  get password() {
    return this.registerForm.controls.password;
  }

  // convenience
  get rePassword(){
    return this.registerForm.controls.rePassword;
  }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(20)] )],
      password: ['', Validators.compose([Validators.required, Validators.minLength(4), Validators.maxLength(20)] )],
      rePassword: ['', Validators.required]
    });
  }

  onSubmit() {
    this.loading = true;
    if (this.registerForm.invalid) {
      return;
    }
    // using custom validators for a form this small, would be overkill
    if (this.password.value !== this.rePassword.value){
      this.error = "Passwords don't match!"
      return;
    }

    // we register the user
    this.authenticationService
      .register(this.username.value, this.password.value)
      .subscribe(
        (data) => {
          this.router.navigate(['/']);
        },
        (error) => {
          this.error = "User already exists!";
          this.loading = false;
        }
      );
  }

}
