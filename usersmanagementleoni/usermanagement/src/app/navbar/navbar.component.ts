import { Component , OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-navbar',
  //standalone: true,
  //imports: [],
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  username: string | null = null;
  /*constructor(private authService: AuthService, private router: Router) {
   
  }*/
  constructor(private authService: AuthService, private router: Router, private translate: TranslateService) {
    translate.addLangs(['en', 'fr']);
    translate.setDefaultLang('en');
  }

  switchLanguage(language: string) {
    this.translate.use(language);
  }
  ngOnInit(): void {
    this.authService.getUsername().subscribe(username => {
      this.username = username;
    });
  }
  logout() {
    this.authService.logout(); // Clear the token
    this.router.navigate(['/login']); // Redirect to the login page
  }
  
  
}
